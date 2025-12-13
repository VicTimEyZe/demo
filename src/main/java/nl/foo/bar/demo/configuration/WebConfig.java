package nl.foo.bar.demo.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import nl.foo.bar.demo.actor.controller.ActorEntityToActorTOConverter;
import nl.foo.bar.demo.actor.controller.ActorTOToActorEntityConverter;
import nl.foo.bar.demo.media.controller.MediaEntityToMediaTOConverter;
import nl.foo.bar.demo.media.controller.MediaTOTypeToMediaTypeEntityConverter;
import nl.foo.bar.demo.mediaconsumption.controller.MediaConsumptionEntityTOMediaConsumptionTOConverter;
import nl.foo.bar.demo.mediaconsumption.controller.MediaConsumptionStatusToMediaConsumptionTOStatus;

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
    registry.addConverter(new MediaConsumptionStatusToMediaConsumptionTOStatus());
  }

}
