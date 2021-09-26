alter table therapist
drop column cities_will_travel;

alter table therapist
drop column cost_of_service;

alter table therapist
drop column picked_hour;

alter table therapist
drop column type_of_massage;

SELECT * FROM ubermassage.therapist;