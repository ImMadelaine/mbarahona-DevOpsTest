CREATE OR REPLACE FUNCTION get_function()
RETURNS TABLE(ID VARCHAR, Nombre VARCHAR, Empresa VARCHAR) AS $$
BEGIN
    RETURN QUERY
    SELECT ID, Nombre, Empresa FROM functionsql;
END;
$$ LANGUAGE plpgsql;