CREATE TABLE assessment
(
    id UUID PRIMARY KEY,

    application_name VARCHAR(255) NOT NULL,

    application_type VARCHAR(100),

    language VARCHAR(100),

    framework VARCHAR(100),

    database VARCHAR(100),

    server_count INTEGER,

    monthly_users INTEGER,

    storage_gb INTEGER,

    traffic_pattern VARCHAR(100),

    current_hosting VARCHAR(255),

    additional_requirements TEXT,

    created_at TIMESTAMP,

    updated_at TIMESTAMP
);