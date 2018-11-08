CREATE TABLE Author (
  id   Integer IDENTITY PRIMARY KEY,
  name VARCHAR(80)
);

CREATE TABLE Book (
  id   Integer IDENTITY PRIMARY KEY,
  title VARCHAR(80)
);
CREATE TABLE Book_Author (
  author_id Integer,
  book Integer
);

