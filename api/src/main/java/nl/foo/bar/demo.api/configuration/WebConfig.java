package nl.foo.bar.demo.api.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import nl.foo.bar.demo.api.actor.ActorEntityToActorTOConverter;
import nl.foo.bar.demo.api.actor.ActorTOToActorEntityConverter;
import nl.foo.bar.demo.api.media.MediaEntityToMediaTOConverter;
import nl.foo.bar.demo.api.media.MediaTOTypeToMediaTypeEntityConverter;
import nl.foo.bar.demo.api.mediaconsumption.MediaConsumptionEntityTOMediaConsumptionTOConverter;
import nl.foo.bar.demo.api.mediaconsumption.MediaConsumptionStatusToMediaConsumptionTOStatusConverter;
import nl.foo.bar.demo.api.mediaconsumption.MediaConsumptionTOStatusToMediaConsumptionStatusConverter;

@Configuration
public class WebConfig implements WebMvcConfigurer {

  @Override
  public void addFormatters(FormatterRegistry registry) {
    registry.addConverter(new ActorEntityToActorTOConverter());
    registry.addConverter(new ActorTOToActorEntityConverter());

    registry.addConverter(new MediaEntityToMediaTOConverter());
    registry.addConverter(new MediaTOTypeToMediaTypeEntityConverter());
    registry.addConverter(new MediaEntityToMediaTOConverter());

    registry.addConverter(new MediaConsumptionEntityTOMediaConsumptionTOConverter());
    registry.addConverter(new MediaConsumptionStatusToMediaConsumptionTOStatusConverter());
    registry.addConverter(new MediaConsumptionTOStatusToMediaConsumptionStatusConverter());
  }

}
