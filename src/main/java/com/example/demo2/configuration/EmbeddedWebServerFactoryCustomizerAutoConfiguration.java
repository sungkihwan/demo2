//package com.example.demo2.configuration;
//
//import org.apache.catalina.startup.Tomcat;
//import org.apache.coyote.UpgradeProtocol;
//import org.springframework.boot.autoconfigure.AutoConfiguration;
//import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
//import org.springframework.boot.autoconfigure.condition.ConditionalOnNotWarDeployment;
//import org.springframework.boot.autoconfigure.condition.ConditionalOnThreading;
//import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
//import org.springframework.boot.autoconfigure.thread.Threading;
//import org.springframework.boot.autoconfigure.web.ServerProperties;
//import org.springframework.boot.autoconfigure.web.embedded.TomcatVirtualThreadsWebServerFactoryCustomizer;
//import org.springframework.boot.autoconfigure.web.embedded.TomcatWebServerFactoryCustomizer;
//import org.springframework.boot.context.properties.EnableConfigurationProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.env.Environment;
//
//@AutoConfiguration
//@ConditionalOnNotWarDeployment
//@ConditionalOnWebApplication
//@EnableConfigurationProperties(ServerProperties.class)
//public class EmbeddedWebServerFactoryCustomizerAutoConfiguration {
//    @Configuration(proxyBeanMethods = false)
//    @ConditionalOnClass({ Tomcat.class, UpgradeProtocol.class })
//    public static class TomcatWebServerFactoryCustomizerConfiguration {
//        @Bean
//        public TomcatWebServerFactoryCustomizer tomcatWebServerFactoryCustomizer(
//                Environment environment,
//                ServerProperties serverProperties
//        ) {
//            return new TomcatWebServerFactoryCustomizer(environment, serverProperties);
//        }
//
//        @Bean
//        @ConditionalOnThreading(Threading.VIRTUAL)
//        TomcatVirtualThreadsWebServerFactoryCustomizer tomcatVirtualThreadsProtocolHandlerCustomizer() {
//            return new TomcatVirtualThreadsWebServerFactoryCustomizer();
//        }
//    }
//}
