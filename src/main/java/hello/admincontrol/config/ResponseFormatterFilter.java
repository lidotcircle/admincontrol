package hello.admincontrol.config;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.WriteListener;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import hello.admincontrol.exception.BaseException;


/**
 * 用于将HTTP请求的返回转化为{@link ResponseBodyFormat}的格式
 * HTTP Status Code:
 *     1. 如果返回体的格式符合 BaseException 那么用 BaseException 中的 code 字段
 *     2. 不满足则不会改变原有的返回状态码
 */
@Component
public class ResponseFormatterFilter extends OncePerRequestFilter {
    static class FilterServletOutputStream extends ServletOutputStream //{
    {
        private ByteArrayOutputStream outputStream;
        public ByteArrayOutputStream getOutputStream() {
            return this.outputStream;
        }

        public FilterServletOutputStream() {
            this.outputStream = new ByteArrayOutputStream();
        }

		@Override
		public boolean isReady() {
			return true;
		}

		@Override
		public void setWriteListener(WriteListener listener) {
            throw new RuntimeException("not implemented");
		}

		@Override
		public void write(int b) throws IOException {
            this.outputStream.write(b);
		}
        
        @Override
        public void write(byte[] b) throws IOException {
            this.outputStream.write(b);
        }

		@Override
        public void write(byte[] b, int off, int len) throws IOException {
            this.outputStream.write(b, off, len);
        }
    } //}
    static class ResponseWrapper extends HttpServletResponseWrapper //{
    {
        FilterServletOutputStream filterOutput;

        public ResponseWrapper(HttpServletResponse response) {
            super(response);
            this.filterOutput = new FilterServletOutputStream();
        }

        @Override
        public FilterServletOutputStream getOutputStream() throws IOException {
            return filterOutput;
        }

        public byte[] getDataStream() {
            return this.filterOutput.getOutputStream().toByteArray();
        }
    } //}

    ObjectMapper objectMapper;
    public ResponseFormatterFilter() {
        this.objectMapper = new ObjectMapper();
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, 
                                    HttpServletResponse response, 
                                    FilterChain chain) throws IOException, ServletException
    {
        ResponseWrapper responseWrapper = new ResponseWrapper(response);
        chain.doFilter(request, responseWrapper);

        int status = responseWrapper.getStatus();
        byte[] data = responseWrapper.getDataStream();

        String msg = null;
        JsonNode j = null;
        try {
            final BaseException e = this.objectMapper.readValue(data, BaseException.class);
            status = e.getCode();
            msg = e.getReason();
            response.setStatus(status);
        } catch (JsonProcessingException e) {}

        if (msg == null) {
            try {
                j = this.objectMapper.readTree(data);
            } catch (JsonProcessingException e){
                msg = new String(data, "UTF-8");
            }
        }

        byte[] nj = this.objectMapper.writeValueAsBytes(new ResponseBodyFormat(status, msg, j));
        response.setContentLength(nj.length);
        response.getOutputStream().write(nj);
    }
}

