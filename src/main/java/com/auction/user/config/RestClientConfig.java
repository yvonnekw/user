package com.auction.user.config;

import org.springframework.beans.factory.annotation.Value;
//import org.springframework.web.client.support.RestClientAdapter;
//import org.springframework.web.service.invoker.HttpServiceProxyFactory;

//@Configuration
public class RestClientConfig {

    //@Value("${addressService.url}")
    //@Autowired
    private  final String addressServiceUrl;

    // Constructor for injecting the property
    public RestClientConfig(@Value("${addressService.url}") String addressServiceUrl) {
        this.addressServiceUrl = addressServiceUrl;
    }

    /*
    @Bean
    public AddressClient addressClient() {
    RestClient restClient = RestClient.builder()
            .baseUrl(addressServiceUrl)
            .build();
    var restClientAdapter = RestClientAdapter.create(restClient);
    var httpServiceProxyFactory = HttpServiceProxyFactory.builderFor(restClientAdapter).build();
    return  httpServiceProxyFactory.createClient(AddressClient.class);
}
*/
}


