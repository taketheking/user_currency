CREATE TABLE `currency` (
                            `id`	bigInt	NOT NULL    PRIMARY KEY	COMMENT 'Auto Increment',
                            `currency_name`	varchar(255)	NOT NULL	COMMENT '통화의 명칭',
                            `exchange_rate`	decimal(38,2)	NOT NULL	COMMENT '해당 통화의 원화 대비 환율',
                            `symbol`	varchar(255)	NOT NULL	COMMENT '통화의 기호',
                            `created_At`	datetime(6)	NOT NULL	COMMENT '통화 정보 입력일',
                            `modified_At`	datetime(6)	NOT NULL	COMMENT '통화 정보 수정일'


);

CREATE TABLE `user` (
                        `id`	bigInt	NOT NULL    PRIMARY KEY	COMMENT 'Auto Increment',
                        `email`	varchar(255)	NOT NULL	COMMENT '사용자의 email',
                        `name`	varchar(255)	NOT NULL	COMMENT '사용자의 이름',
                        `created_At`	datetime(6)	NOT NULL	COMMENT '사용자 정보 입력일',
                        `modified_At`	datetime(6)	NOT NULL	COMMENT '사용자 정보 수정일'
);

CREATE TABLE `exchange` (
                            `id`	bigInt	NOT NULL    PRIMARY KEY COMMENT 'Auto Increment',
                            `amount_in_krw`	decimal(38,2)	NOT NULL	COMMENT '환전하기전 원화 금액(krw)',
                            `amount_after_exchange`	decimal(38,2)	NOT NULL	COMMENT '환전한 후 해당 통화 금액',
                            `status`	enum('NORMAL', 'CANCELLED')    NOT NULL	COMMENT 'normal : 환전 완료, cancelled : 환전 취소',
                            `created_At`	datetime(6)	NOT NULL	COMMENT '환전 요청 입력일',
                            `modified_At`	datetime(6)	NOT NULL	COMMENT '환전 요청 수정일(=취소일)',
                            `currency_id`	bigInt	NOT NULL	COMMENT 'currency 외래키',
                            `user_id`	bigInt	NOT NULL	COMMENT 'user 외래키',
                            FOREIGN KEY (currency_id) REFERENCES currency(id),
                            FOREIGN KEY (user_id) REFERENCES user(id)
);


-- Insert user
INSERT INTO user (id, email, name, created_at, modified_At)
VALUES (1, 'kim', 'sparta@teamsparta.co.kr', '2024-10-29 20:11:02', '2024-10-30 15:11:02');

-- Insert currency
INSERT INTO currency (id, currency_name, exchange_rate, symbol, created_at, modified_At)
VALUES (1,'USD','1390', '$' , '2024-10-29 09:12:40', '2024-10-30 19:20:24');

-- Insert exchange
INSERT INTO exchange (id, amount_in_krw, amount_after_exchange, status, created_at, modified_At, currency_id, user_id)
VALUES (1,'10000','74.63', 'NORMAL' , '2024-10-29 09:12:40', '2024-10-30 19:20:24', 1, 1);


-- Select user
SELECT * FROM user;

-- Select currency
SELECT * FROM currency;


-- Select user with id 1
SELECT * FROM user WHERE id = 1;

-- Select currency with id 1
SELECT * FROM currency WHERE id = 1;


-- Select exchange with UserId 1
SELECT * FROM exchange ex WHERE ex.user_id = 1;

-- Select exchange group by UserId 1
SELECT * FROM exchange ex join user u
            WHERE ex.status = :status and u.id = 1
            group by u.id;

-- Update exchange
UPDATE exchange SET status = 'CANCELLED'  WHERE id = 1;


-- Delete user with id 1
DELETE FROM user WHERE id = 1;