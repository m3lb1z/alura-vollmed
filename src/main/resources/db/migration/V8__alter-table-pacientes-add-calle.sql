ALTER TABLE pacientes ADD calle VARCHAR(255);
UPDATE pacientes SET calle = '';
ALTER TABLE pacientes MODIFY calle VARCHAR(255);