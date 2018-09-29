
INSERT INTO banners VALUES ('004bffc2-1469-4548-8489-95e3af6b7af8' ,'http://lyalya.ru/logo1.jpg', 450, 450, 'http://target1','en-US', 0 );
INSERT INTO banners  VALUES ('0d1de3f1-d66b-48db-ab32-d8b31db0cb6c', 'http://lyalya.ru/logo2.jpg', 500, 500, 'http://target2', 'ru-RU', 0);
INSERT INTO banners  VALUES ('bbecccc6-381a-4b65-bc04-5fcd1bb3383d', 'http://lyalya.ru/logo3.jpg', 600, 600, 'http://target3', 'en-US' , 0);
INSERT INTO banners   VALUES ('4f7ef9ff-4aff-4488-9098-821ef267cd40', 'http://lyalya.ru/logo4.jpg', 700, 600, 'http://target4', 'ru-RU', 0);

INSERT INTO admins VALUES ('aaa09229-b872-4bd9-a4a9-34bdf09761cd', 'igoresha','hash12345', 0);
INSERT INTO admins  VALUES ('92f85411-e8d7-4cc1-b378-8385733a307b', 'vanya','hash67890', 0);



INSERT INTO AUDIT VALUES ('f46cb077-a9f5-46ef-893d-4265aa8120ae', 'aaa09229-b872-4bd9-a4a9-34bdf09761cd', '0d1de3f1-d66b-48db-ab32-d8b31db0cb6c', 'UPDATE' , 'width', '450', '2000', '06:49/25.09.18' );
INSERT INTO AUDIT VALUES ('a964432c-a044-471c-ae19-10a1734e4951', 'aaa09229-b872-4bd9-a4a9-34bdf09761cd', '0d1de3f1-d66b-48db-ab32-d8b31db0cb6c', 'CREATE' , null, null, null , '06:49/25.09.18');
INSERT INTO AUDIT VALUES ('2daebee6-7cb9-487c-a55f-141a41049694', 'aaa09229-b872-4bd9-a4a9-34bdf09761cd', '4f7ef9ff-4aff-4488-9098-821ef267cd40', 'UPDATE' , 'width', '4550', '450', '07:49/25.09.18' );
INSERT INTO AUDIT VALUES ('5646796f-3be1-4140-bd14-148cc1e76011', '92f85411-e8d7-4cc1-b378-8385733a307b', '0d1de3f1-d66b-48db-ab32-d8b31db0cb6c', 'UPDATE' , 'width', '450', '2000', '08:49/25.09.18' );

