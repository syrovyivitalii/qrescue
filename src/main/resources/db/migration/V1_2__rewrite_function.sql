CREATE OR REPLACE FUNCTION create_head_osbb_data()
RETURNS TRIGGER AS $$
BEGIN
INSERT INTO head_osbb (id_osbb) VALUES (NEW.id);
INSERT INTO data (id) VALUES (NEW.id);
RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER osbb_after_insert
    AFTER INSERT ON osbb
    FOR EACH ROW
    EXECUTE FUNCTION create_head_osbb_data();
