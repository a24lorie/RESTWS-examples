package org.alb.sse.jersey.listeners;

import java.io.IOException;
import java.util.Date;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.glassfish.jersey.media.sse.EventOutput;
import org.glassfish.jersey.media.sse.OutboundEvent;
import org.glassfish.jersey.media.sse.SseFeature;

/**
 * @author alorie
 *
 */
@Path("/timer")
public class JerseyTimer 
{
    @GET
    @Produces(SseFeature.SERVER_SENT_EVENTS)
    public EventOutput getServerSentEvents() {
	final EventOutput eventOutput = new EventOutput();
	new Thread(new Runnable() {
	    @Override
	    public void run() {
		try {
		    while(true){
			Thread.sleep(1000);

			final OutboundEvent.Builder eventBuilder
			= new OutboundEvent.Builder();
			eventBuilder.name("time");
			eventBuilder.data(String.class, new Date().toString());

			final OutboundEvent event = eventBuilder.build();
			eventOutput.write(event);
		    }
		} catch (IOException | InterruptedException e) {
		    throw new RuntimeException("Error when writing the event.", e);
		} finally {
		    try {
			eventOutput.close();
		    } catch (IOException ioClose) {
			throw new RuntimeException(
				"Error when closing the event output.", ioClose);
		    }
		}
	    }
	}).start();
	return eventOutput;
    }
}
