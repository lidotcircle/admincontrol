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

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import hello.admincontrol.exception.BaseException;


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

        if(status >= 400) {
            response.setStatus(200);
        }

        String msg = null;
        JsonNode j = null;
        try {
            final BaseException e = this.objectMapper.readValue(data, BaseException.class);
            status = e.getCode();
            msg = e.getReason();
        } catch (JsonProcessingException e) {}

        if (msg == null) {
            try {
                j = this.objectMapper.readTree(data);
            } catch (JsonProcessingException e){
                status = HttpStatus.INTERNAL_SERVER_ERROR.value();
                msg = HttpStatus.INTERNAL_SERVER_ERROR.toString();
            }
        }

        byte[] nj = this.objectMapper.writeValueAsBytes(new ResponseBodyFormat(status, msg, j));
        response.setContentLength(nj.length);
        response.getOutputStream().write(nj);
    }
}

