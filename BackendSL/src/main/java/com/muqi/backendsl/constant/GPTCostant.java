package com.muqi.backendsl.constant;

public interface GPTCostant {

    String serverAddress = "172.31.92.56";

    Integer serverPort   = 8083;

    Integer bufferSize   = 4096;

    String prefix        = "I am a college student, please list urls of four widely-used websites for learning ";

    String suffix        = ", please wrap each url with (), and do not include anything other than url in ()."
                            + "Please make sure these urls are accessible!"+ "Please give me another github project url about this topic.";


}
