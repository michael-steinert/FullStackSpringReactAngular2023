ALTER TABLE customer
ADD COLUMN image_id VARCHAR(36);

ALTER TABLE customer
ADD CONSTRAINT image_id_unique UNIQUE (image_id);
