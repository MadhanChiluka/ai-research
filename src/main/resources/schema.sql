CREATE EXTENSION IF NOT EXISTS vector;
CREATE EXTENSION IF NOT EXISTS hstore;
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE IF NOT EXISTS vector_store (
    id uuid DEFAULT uuid_generate_v4() PRIMARY KEY,
    content text,
    metadata json,
    embedding vector(1536)
);

-- Correct way to create an HNSW index with cosine distance
CREATE INDEX IF NOT EXISTS idx_vector_store_embedding
ON vector_store USING hnsw (embedding vector_cosine_ops);
