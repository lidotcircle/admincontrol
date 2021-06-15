package hello.admincontrol.controller;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/apis/ping")
class Ping {
    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(Ping.class);

    static class PingResp {
        private String serviceName;
        public String getServiceName() {
            return this.serviceName;
        }
        public void setServiceName(String serviceName) {
            this.serviceName = serviceName;
        }
        
        private String serviceVersion;
        public String getServiceVersion() {
            return this.serviceVersion;
        }
        public void setServiceVersion(String serviceVersion) {
            this.serviceVersion = serviceVersion;
        }

        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", shape = JsonFormat.Shape.STRING)
        private Date date;
        public Date getDate() {
            return this.date;
        }
        public void setDate(Date date) {
            this.date = date;
        }

        public PingResp() {
            this.serviceName = "admincontrol";
            this.serviceVersion = "1.0.0";
            this.date = new Date();
        }
    }

    @GetMapping()
    private PingResp ping() {
        log.info("Recieve a ping");
        return new PingResp();
    }
}

