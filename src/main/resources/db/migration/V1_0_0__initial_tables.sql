CREATE TABLE actor
(
  id            uuid DEFAULT random_uuid() PRIMARY KEY,
  name          VARCHAR(20) NOT NULL,
  email         VARCHAR(50) NOT NULL UNIQUE,
  date_of_birth date
);

CREATE TABLE media
(
  id     uuid DEFAULT random_uuid() PRIMARY KEY,
  title  VARCHAR(100) NOT NULL UNIQUE,
  artist VARCHAR(100) NOT NULL,
  type   ENUM('MOVIE', 'PODCAST', 'BOOK', 'GAME') NOT NULL
);

CREATE TABLE media_consumption
(
  actor_id uuid REFERENCES actor (id),
  media_id uuid REFERENCES media (id),
  status   ENUM('CONSUMING', 'COMPLETED', 'PAUSED', 'DROPPED') NOT NULL,
  PRIMARY KEY (actor_id, media_id)
);