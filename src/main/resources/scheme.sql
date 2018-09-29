CREATE TABLE BANNERS ( id VARCHAR(40) NOT NULL PRIMARY KEY,
                        imgSrc VARCHAR(255) NOT NULL,
                        width INTEGER NOT NULL,
                        height INTEGER NOT NULL,
                        targetUrl VARCHAR(255) NOT NULL,
                        langId VARCHAR(16) NOT NULL,
                        isDeleted BIT NOT NULL
                        );

CREATE TABLE ADMINS (id VARCHAR(40) NOT NULL PRIMARY KEY,
                      name VARCHAR(64) NOT NULL UNIQUE,
                      hash VARCHAR(64) NOT NULL,
                      isDeleted BIT NOT NULL
                    );

CREATE TABLE AUDIT (id VARCHAR(40) NOT NULL PRIMARY KEY,
                      idAdmin VARCHAR(40) NOT NULL, FOREIGN KEY(idAdmin) REFERENCES ADMINS(id),
                      idBanner VARCHAR(40) NOT NULL, FOREIGN KEY(idBanner) REFERENCES BANNERS(id),
                      operationType VARCHAR(10) NOT NULL CHECK(
                      operationType = 'UPDATE' OR
                      operationType = 'DELETE' OR
                      operationType = 'CREATE'),
                      columnName VARCHAR(10),
                      oldValue VARCHAR(255),
                      newValue VARCHAR(255),
                      recordDate VARCHAR (20) NOT NULL
                    );