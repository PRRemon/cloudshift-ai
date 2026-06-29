CREATE TABLE recommendation
(
    id UUID PRIMARY KEY,

    assessment_id UUID NOT NULL,

    provider VARCHAR(100) NOT NULL,

    recommendation TEXT,

    raw_ai_response TEXT,

    tokens_used INTEGER,

    created_at TIMESTAMP NOT NULL,

    CONSTRAINT fk_recommendation_assessment
        FOREIGN KEY (assessment_id)
        REFERENCES assessment(id)
        ON DELETE CASCADE
);
