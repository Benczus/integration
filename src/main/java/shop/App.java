package shop;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.InboundChannelAdapter;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.http.inbound.HttpRequestHandlingMessagingGateway;
import org.springframework.integration.http.inbound.RequestMapping;
import org.springframework.integration.http.outbound.HttpRequestExecutingMessageHandler;
import org.springframework.integration.stream.CharacterStreamWritingMessageHandler;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import shop.integration.dto.ProductDTO;
import shop.integration.dto.ShopDTO;
import shop.systems.gateway.DummyGateway;
import sun.util.calendar.BaseCalendar;


import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;


/**
 * Hello world!
 */
@Configuration
@ComponentScan
@EnableIntegration
@IntegrationComponentScan("shop.systems.gateway")
public class App {
//
    @Autowired
    private Environment env;
//
//    @Bean
//    public HttpRequestExecutingMessageHandler httpGateway(){
//        HttpRequestExecutingMessageHandler gateway = new HttpRequestExecutingMessageHandler("http://localhost:8088/shop/");
//        gateway.setHttpMethod(HttpMethod.GET);
//        gateway.setExpectedResponseType(ShopDTO.class);
//        gateway.setOutputChannel(requestChannel());
//        return  gateway;
//    }
//
//    @Bean
//    public MessageChannel requestChannel() {
//        return new DirectChannel();
//    }
//
//    @Bean
//    public MessageChannel shopReceiverChannel(){
//        return new DirectChannel();
//    }
//
//    @Bean
//    public MessageChannel shopReceiverChannelBetterVersion(){
//        return new DirectChannel();
//    }
//
//    @Bean
//    public MessageChannel almaChannel(){
//        MessageChannel channel =new DirectChannel();
//
//        return  channel;
//    }

//    @Bean
//    public MessageChannel replyChannel() {
//        MessageChannel result = new DirectChannel();
//        CharacterStreamWritingMessageHandler handler = new CharacterStreamWritingMessageHandler(new BufferedWriter(new OutputStreamWriter(System.out)));
//
//        return  result;
//    }


//    @Bean
//    @ServiceActivator(inputChannel = "shopReceiverChannelBetterVersion")
//    public HttpRequestExecutingMessageHandler httpGateway(){
//
//        HttpRequestExecutingMessageHandler gateway = new HttpRequestExecutingMessageHandler("http://localhost:8088/shop/");
//        gateway.setHttpMethod(HttpMethod.GET);
//        gateway.setExpectedResponseType(ShopDTO.class);
//        gateway.setOutputChannel(almaChannel());
//        return gateway;
//    }






    @Bean
    public String hello(){
        return "alma";
    }

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(App.class);
        System.out.println(context.getBean("hello"));

//        Message<String> msg = MessageBuilder.withPayload("Hello").build();
//        MessageChannel channel = (MessageChannel) context.getBean("requestChannel");
//        channel.send(msg);

//        Message<String> msg = MessageBuilder.withPayload("Hello Bence").build();
//        MessageChannel channel = (MessageChannel) context.getBean("almaChannel");
//        channel.send(msg);


//        System.out.println("--------------");
//
//        Message<String> msg1 = MessageBuilder.withPayload("Hello Bence").build();
//        MessageChannel channel1 = (MessageChannel) context.getBean("shopReceiverChannelBetterVersion");
//        channel1.send(msg1);
//
//        System.out.println("--------------");

        DummyGateway gateway = (DummyGateway) context.getBean("dummyGateway");
        gateway.dummyMethod();

        List<ProductDTO> productlist= gateway.listProducts();

        for (ProductDTO productDTO : productlist
             ) {
            System.out.println(productDTO.getItemName());

        }

        System.out.println("\nbye bye");



        /*
        Message <LocalDate> shopMsg=MessageBuilder.withPayload(LocalDate.now()).build();
        MessageChannel shopChannel=context.getBean("shopReceiverChannel", MessageChannel.class);
        shopChannel.send(shopMsg);
        */
//        HttpRequestExecutingMessageHandler gateway = context.getBean("httpGateway",HttpRequestExecutingMessageHandler.class);

//        ConfigurableApplicationContext context = SpringApplication.run(App.class, args);
//        System.in.read();
//        context.close();
/*
        //ApplicationContext context = new ClassPathXmlApplicationContext("/si-config.xml");

        MessageChannel stdOut = context.getBean("messageChannel", MessageChannel.class);

        Message<String> message = MessageBuilder.withPayload("Hello World!").build();




//        MessageChannel stdOut = context.getBean("shop1ReceiverChannel", MessageChannel.class);
//
//        Message<String> message = MessageBuilder.withPayload("Hello World!").build();



        System.out.println("Sout says stdOut is:" + stdOut);
        stdOut.send(message);

        ShopGateway shopGateway = context.getBean("ShopGateway", ShopGateway.class);
       System.out.println(shopGateway.listProducts());

        ShopComparator shopComparator = new ShopComparator();


        ArrayList<ShopDTO> shopList = shopGateway.listProducts();
        ShopDTO shopDTO1 = shopList.get(0);
        ShopDTO shopDTO2 = shopList.get(1);


        System.out.println("FIRST SHOP DATA:");

        for (ProductDTO productDTO : shopDTO1.getProductList().getProduct()
                ) {
            System.out.println(productDTO.getItemName());
            System.out.println(productDTO.getPrice());
            System.out.println(productDTO.getManufacturer() + "\n \n");

        }

        System.out.println("SECOND SHOP DATE");
        for (ProductDTO productDTO : shopDTO2.getProductList().getProduct()
                ) {
            System.out.println(productDTO.getItemName());
            System.out.println(productDTO.getPrice());
            System.out.println(productDTO.getManufacturer() + "\n \n");

        }


        System.out.println("cicus cicus " + shopDTO1 + " " + shopDTO2);

        Collection<ProductDTO> bestProd;

        bestProd = shopComparator.getBestPrices(shopDTO1, shopDTO2);

        System.out.println(bestProd);
        for (ProductDTO productDTO : bestProd) {
            System.out.println(productDTO.getItemName());
            System.out.println(productDTO.getPrice());
            System.out.println(productDTO.getManufacturer() + "\n");


        }


//        Message message1= MessageBuilder.withPayload("cica").setHeader("shopNumber", "shop2").build();


//        ShopDTO shopDTO= shopGateway.listProductsByShop(message1);
        ShopDTO shopDTO = shopGateway.listProductsByShop("shop1");
        System.out.println(shopDTO.getShopID() + "" + shopDTO.getShopName());
        // System.out.println(shopDTO.getShopID());





//   TODO NOT WORKING
//        for (ProductDTO product : gateway.listProducts()) {
//            System.out.println(product.getItemName()+" "+ product.getItemID()+" "+product.getManufacturer());
//        }

//        ProductDTO.Warranty warranty= new ProductDTO.Warranty();
//        ProductDTO.Warranty.Private privatea=new ProductDTO.Warranty.Private();
//        privatea.setCompany(2);
//        privatea.setValue(4);
//        warranty.setPrivate(privatea);
//        ProductDTO prod= new ProductDTO();
//        prod.setItemName("CICA");
//        prod.setItemID(UUID.randomUUID().toString());
//        prod.setManufacturer("Macsek");
//        prod.setDescription("");
//        prod.setWarranty(warranty);
//        prod.setPrice(255);
//
//        gateway.addProductToShop(prod, new ShopDTO());
*/
        }

    }











