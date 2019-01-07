insert into jobtitels(naam, versie) values ('test', 0);
insert into werknemers(familienaam, voornaam, email, jobtitelid, salaris, paswoord, geboorte, rijksregisternr, versie) 
	values ('test', 'test', 'test@test.com', (select id from jobtitels where naam='test'), 2000, 'test', '1999-01-01', 1, 0);
insert into werknemers(familienaam, voornaam, email, chefid, jobtitelid, salaris, paswoord, geboorte, rijksregisternr, versie) 
	values ('testOndergeschikte1', 'testOndergeschikte1', 'testOndergeschikte1@test.com', (select id from werknemers where familienaam='test'), (select id from jobtitels where naam='test'), 1800, 'testOndergeschikte1', '1999-01-02', 2, 0); 	