INSERT INTO eg_appconfig(id, key_name, description, module) VALUES (nextval('seq_eg_appconfig'), 'SHOW_SERVICE_FIELDS', 'To show Service VAT fields in Estimate screen', (select id from eg_module where name='Works Management'));
INSERT INTO eg_appconfig_values(id, key_id, effective_from, value, version) VALUES (nextval('seq_eg_appconfig_values'), (select id from eg_appconfig where key_name='SHOW_SERVICE_FIELDS'), now(), 'No', 0);


--rollback delete from eg_appconfig_values where key_id in(select id from eg_appconfig where key_name='SHOW_SERVICE_FIELDS');
--rollback delete from eg_appconfig where key_name='SHOW_SERVICE_FIELDS';