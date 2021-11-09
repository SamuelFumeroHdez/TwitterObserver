package es.ull.twitter;

import twitter4j.Trends;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

public class TwitterConector {
    private Twitter twitter;
    public TwitterConector(){
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDaemonEnabled(true)
                .setOAuthConsumerKey("iRqa9hqC8ydRw0dk5kkgqZjCW")
                .setOAuthConsumerSecret("HWLiI5T35IiU5X5wtA1IxM8UnO02e02SJODzgOpL61dW6sTi6C")
                .setOAuthAccessToken("1136742048080568330-nE9bgDeHXARMO34O5BwGh6nAXpgog0")
                .setOAuthAccessTokenSecret("gkooBOJL6bgE0ZSVmrSW2pE6geYfGOC78p046UbLNo9ru");

        TwitterFactory tf = new TwitterFactory(cb.build());
        this.twitter = tf.getInstance();
    }
    public Twitter getTwitter(){
        return this.twitter;
    }
}
