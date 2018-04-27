-- users
-- insert admin + roles + join table
-- insert into account (email, password, username, name) values('oktato12@elte.hu', '$2a$11$J3.Nv8KCRtMPc3a3qXjKh.pXBbSKi62tyMBunFHt.UfVCJBby3OE6', 'oktato12', 'Oktató  István')
-- insert into account (email, password, username, name) values('hallagto@elte.hu', '$2a$11$Ab4FyY/KcQFe/JMbzLTjMeySQJLaSgq8i1xWRQ/s.ai.Fl3BtMa.u', 'hallgato', 'Hallgató János')

-- categories
-- insert into category (name) values('Információs rendszerek')
-- insert into category (name) values('Adattudomány')
-- insert into category (name) values('Párhuzamos programozás')
-- insert into category (name) values('Játék')
-- insert into category (name) values('Mobilalkalmazás')

-- role
-- insert into role(name) values('SUPERVISOR')

--supervisors
-- insert into supervisor (name, office_hours, phone, room, title, website, workplace, account, confirmed, external) values('Oktató  István', 'Hétfő 10.00-12.00', '/7452', '2.145', 'Adjunktus', 'oktatoistvan.web.elte.hu', 'Információs Rendszerek Tanszék', 9, true, false)

-- account_roles
-- insert into account_roles (account_id, roles_id) values(7, 1)

-- topic
--insert into topic (name, type, category, description, status, supervisor, archive, creation_date) values('Adatbázisok összehasonlítása', 'MSC_THESIS', 1, 'Az iparban jelenleg is használt 10 legnépszerűbb adatbázis összehasonlítása megadott paraméterek alapján.', 'OPEN', 7, false, '2017-03-14')


select * from account