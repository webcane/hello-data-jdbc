CREATE EXTENSION IF NOT EXISTS "pgcrypto";

CREATE TABLE IF NOT EXISTS chat_game (
    id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
    chat_id BIGINT NOT NULL UNIQUE,
    last_message_id INTEGER
);

CREATE TABLE IF NOT EXISTS guess_game (
    game_id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
    complexity INTEGER CHECK (complexity > 0 AND complexity < 10),
    secret INTEGER[],
    ordinal INTEGER NOT NULL CHECK (ordinal > 0),
    chat_game UUID NOT NULL REFERENCES chat_game(id)
);
