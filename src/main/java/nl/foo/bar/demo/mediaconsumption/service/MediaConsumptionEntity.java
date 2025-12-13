package nl.foo.bar.demo.mediaconsumption.service;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import nl.foo.bar.demo.actor.service.ActorEntity;
import nl.foo.bar.demo.media.service.MediaEntity;

@Entity(name = "media_consumption")
@Data
@IdClass(MediaConsumptionEntityId.class)
public class MediaConsumptionEntity {

  @Id
  @ManyToOne(optional = false,
             fetch = FetchType.EAGER)
  private ActorEntity actor;

  @Id
  @ManyToOne(optional = false,
             fetch = FetchType.EAGER)
  private MediaEntity media;

  @Enumerated(EnumType.STRING)
  @Column(name = "status")
  private MediaConsumptionStatus mediaConsumptionStatus;

}
