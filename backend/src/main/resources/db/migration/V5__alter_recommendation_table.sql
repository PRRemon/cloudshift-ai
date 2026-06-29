ALTER TABLE recommendation
DROP COLUMN recommendation;

ALTER TABLE recommendation
ADD COLUMN compute VARCHAR(255);

ALTER TABLE recommendation
ADD COLUMN database_name VARCHAR(255);

ALTER TABLE recommendation
ADD COLUMN storage VARCHAR(255);

ALTER TABLE recommendation
ADD COLUMN networking VARCHAR(255);

ALTER TABLE recommendation
ADD COLUMN estimated_monthly_cost VARCHAR(255);

ALTER TABLE recommendation
ADD COLUMN migration_complexity VARCHAR(255);

ALTER TABLE recommendation
ADD COLUMN security_recommendations TEXT;

ALTER TABLE recommendation
ADD COLUMN risks TEXT;
