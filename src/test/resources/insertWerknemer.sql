insert into jobtitels(naam, versie) values ('test', 0);
insert into werknemers(familienaam, voornaam, email, jobtitelid, salaris, paswoord, geboorte, rijksregisternr, versie) 
	values ('test', 'test', 'test@test.com', (select id from jobtitels where naam='test'), 2000, 'test', '1999-01-01', 1, 0); 