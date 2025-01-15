CREATE EXTENSION IF NOT EXISTS "pgcrypto";

CREATE TABLE IF NOT EXISTS chat_game (
    id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
    chat_id BIGINT NOT NULL UNIQUE,
    last_message_id INTEGER,
    game UUID
);

CREATE TABLE IF NOT EXISTS guess_game (
    game_id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
    complexity INTEGER CHECK (complexity > 0 AND complexity < 10),
    secret INTEGER[],
	chat UUID NOT NULL
);

ALTER TABLE chat_game
ADD CONSTRAINT fk_chat_game_guess_game
FOREIGN KEY (game) REFERENCES guess_game(game_id);

ALTER TABLE guess_game
ADD CONSTRAINT fk_guess_game_chat_game
FOREIGN KEY (chat) REFERENCES chat_game(id);
