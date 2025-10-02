-- Roles
INSERT INTO shroomshare.role(id, name) VALUES (default, 'admin');
INSERT INTO shroomshare.role(id, name) VALUES (default, 'user');

-- Users
INSERT INTO shroomshare.user(id, role_id, username, password, status) VALUES
                                                                          (default, 1, 'admin', '123', 'A'),
                                                                          (default, 2, 'user', '123', 'A'),
                                                                          (default, 2, 'testuser', 'password', 'A'),
                                                                          (default, 2, 'jaan', 'salasona', 'A'),
                                                                          (default, 2, 'liisa', 'salasona', 'A');

-- Profiles
INSERT INTO shroomshare.profile(id, user_id, first_name, last_name, email, description) VALUES
                                                                                            (default, 1, 'Admin', 'User', 'admin@example.com', 'Süsteemi administraator'),
                                                                                            (default, 2, 'Tavakasutaja', 'Näide', 'user@example.com', 'Testkasutaja'),
                                                                                            (default, 3, 'Test', 'Kasutaja', 'test@example.com', 'Profiili testimine'),
                                                                                            (default, 4, 'Jaan', 'Tamm', 'jaan.tamm@example.com', 'Looduse ja seente entusiast'),
                                                                                            (default, 5, 'Liisa', 'Kask', 'liisa.kask@example.com', 'Armastan metsa ja piknikke');

-- Locations
INSERT INTO shroomshare.location(id, user_id, name, latitude, longitude, description, created, last_active, status, avg_rating) VALUES
                                                                                                                                    (default, 1, 'Tallinna Rocca al Mare mets', 59.4289, 24.6412, 'Linna lähedal asuv mets, palju männiseeni', CURRENT_DATE, CURRENT_DATE, 'A', 4.5),
                                                                                                                                    (default, 1, 'Tartu Raadi mets', 58.3875, 26.7456, 'Ülikooli lähedal, rohkelt puravik', CURRENT_DATE, CURRENT_DATE, 'A', 4.2),
                                                                                                                                    (default, 2, 'Pärnu Valgeranna rand', 58.3819, 24.4756, 'Rannamets, leidub kukeseeni', CURRENT_DATE, CURRENT_DATE, 'A', 3.8),
                                                                                                                                    (default, 1, 'Narva-Jõesuu männimets', 59.4536, 28.0408, 'Puhta õhuga männimets', CURRENT_DATE, CURRENT_DATE, 'A', 4.0),
                                                                                                                                    (default, 2, 'Haapsalu Uuemõisa park', 58.9433, 23.5412, 'Vana parkmets, erinevaid seeneliike', CURRENT_DATE, CURRENT_DATE, 'A', 3.5),
                                                                                                                                    (default, 1, 'Viljandi Paala mets', 58.3639, 25.5897, 'Segamets järve ääres', CURRENT_DATE, CURRENT_DATE, 'A', 4.3),
                                                                                                                                    (default, 2, 'Rakvere Vallimäe ümbrus', 59.3467, 26.3558, 'Ajaloolise kindluse lähedal', CURRENT_DATE, CURRENT_DATE, 'A', 3.9),
                                                                                                                                    (default, 1, 'Kuressaare Smuuli mets', 58.2489, 22.4856, 'Saare suurim seenekoht', CURRENT_DATE, CURRENT_DATE, 'A', 4.6),
                                                                                                                                    (default, 1, 'Võru Tamula järve ümbrus', 57.8392, 27.0156, 'Niiske mets, palju pilve', CURRENT_DATE, CURRENT_DATE, 'A', 4.1),
                                                                                                                                    (default, 2, 'Põlva Karilatsi mets', 58.0567, 27.0489, 'Kaunis segamets', CURRENT_DATE, CURRENT_DATE, 'A', 3.7),
                                                                                                                                    (default, 1, 'Paide Kesklinna park', 58.8856, 25.5571, 'Pargimets kesklinna lähedal', CURRENT_DATE, CURRENT_DATE, 'A', 3.6),
                                                                                                                                    (default, 2, 'Jõhvi Kukruse mets', 59.3578, 27.4123, 'Põlevkivi piirkonna mets', CURRENT_DATE, CURRENT_DATE, 'A', 3.4),
                                                                                                                                    (default, 1, 'Valga piiri-äärne mets', 57.7789, 26.0456, 'Piiriääre segamets', CURRENT_DATE, CURRENT_DATE, 'A', 4.0),
                                                                                                                                    (default, 1, 'Kärdla Hiiumaa keskpark', 58.9978, 22.7489, 'Saare siseosa mets', CURRENT_DATE, CURRENT_DATE, 'A', 3.8),
                                                                                                                                    (default, 2, 'Elva Verevi järve mets', 58.2245, 26.4178, 'Järveääre niiske mets', CURRENT_DATE, CURRENT_DATE, 'A', 4.2),
                                                                                                                                    (default, 1, 'Keila Joa park', 59.3023, 24.2856, 'Joa ümbruse vana park', CURRENT_DATE, CURRENT_DATE, 'A', 4.4),
                                                                                                                                    (default, 2, 'Saaremaa Vilsandi lähedal', 58.3889, 21.8456, 'Meremets rohke taimestikuga', CURRENT_DATE, CURRENT_DATE, 'A', 3.9),
                                                                                                                                    (default, 1, 'Rapla Mahtra mets', 59.0089, 24.7923, 'Keskmaasuurune segamets', CURRENT_DATE, CURRENT_DATE, 'A', 3.5),
                                                                                                                                    (default, 1, 'Jõgeva Pedja jõe äär', 58.7467, 26.3945, 'Jõeäärne niiskusmets', CURRENT_DATE, CURRENT_DATE, 'A', 4.1),
                                                                                                                                    (default, 2, 'Tapa raudteejaama mets', 59.2612, 25.9589, 'Vaikne mets jaama taga', CURRENT_DATE, CURRENT_DATE, 'A', 3.3);

-- Mushrooms
INSERT INTO shroomshare.shroom(id, user_id, name, description, status) VALUES
                                                                           (default, 1, 'Harilik kukeseen', 'Kollase või oranži kübaraga, väga maitsev ja populaarne', 'A'),
                                                                           (default, 1, 'Männiseen', 'Punakaspruuni kübaraga, kasvab männimetsas', 'A'),
                                                                           (default, 1, 'Puravik', 'Hele kübaraga, sobib hästi suppidesse', 'A'),
                                                                           (default, 2, 'Pilvik', 'Haruldane, valge kübaraga, väga hinnatud', 'A'),
                                                                           (default, 1, 'Vaheliku riisikas', 'Oranži lehvikujuliste lehtedega, kasvab kuusemetsas', 'A'),
                                                                           (default, 2, 'Hall puraviku seen', 'Hall või pruunikas, kasvab gruppides', 'A'),
                                                                           (default, 1, 'Mustikaseen', 'Väike tume kübaraga, kasvab mustikate vahel', 'A'),
                                                                           (default, 2, 'Kollane lehmakeel', 'Eredkollane ketasjas seen, kasvab männidel', 'A'),
                                                                           (default, 1, 'Hapu torik', 'Kollakasroheline kübaraga lehterseen', 'A'),
                                                                           (default, 2, 'Punane kärbseseen', 'Punane valgete täppidega, mürgine kuid ilus', 'A');

-- Link mushrooms to locations
INSERT INTO shroomshare.shroom_location(id, location_id, shroom_id) VALUES
                                                                        (default, 1, 1), (default, 1, 2), (default, 1, 5),
                                                                        (default, 2, 3), (default, 2, 7), (default, 3, 1),
                                                                        (default, 4, 2), (default, 4, 5), (default, 5, 3),
                                                                        (default, 6, 1), (default, 6, 4), (default, 7, 8),
                                                                        (default, 8, 1), (default, 8, 6), (default, 9, 4),
                                                                        (default, 10, 3), (default, 10, 9), (default, 11, 2),
                                                                        (default, 12, 7), (default, 13, 1), (default, 14, 10),
                                                                        (default, 15, 3), (default, 16, 1), (default, 17, 5),
                                                                        (default, 18, 6), (default, 19, 4), (default, 20, 8);

-- Location Images
INSERT INTO shroomshare.location_image(id, location_id, image_data) VALUES
                                                                        (default, 1, 'https://images.unsplash.com/photo-1511497584788-876760111969'),
                                                                        (default, 2, 'https://images.unsplash.com/photo-1542273917363-3b1817f69a2d'),
                                                                        (default, 3, 'https://images.unsplash.com/photo-1473448912268-2022ce9509d8'),
                                                                        (default, 4, 'https://images.unsplash.com/photo-1475924156734-496f6cac6ec1'),
                                                                        (default, 5, 'https://images.unsplash.com/photo-1502082553048-f009c37129b9'),
                                                                        (default, 6, 'https://images.unsplash.com/photo-1507003211169-0a1dd7228f2d'),
                                                                        (default, 7, 'https://images.unsplash.com/photo-1448375240586-882707db888b'),
                                                                        (default, 8, 'https://images.unsplash.com/photo-1441974231531-c6227db76b6e'),
                                                                        (default, 9, 'https://images.unsplash.com/photo-1469474968028-56623f02e42e'),
                                                                        (default, 10, 'https://images.unsplash.com/photo-1506905925346-21bda4d32df4'),
                                                                        (default, 11, 'https://images.unsplash.com/photo-1504280390367-361c6d9f38f4'),
                                                                        (default, 12, 'https://images.unsplash.com/photo-1542273917363-3b1817f69a2d'),
                                                                        (default, 13, 'https://images.unsplash.com/photo-1497436072909-60f360e1d4b1'),
                                                                        (default, 14, 'https://images.unsplash.com/photo-1511497584788-876760111969'),
                                                                        (default, 15, 'https://images.unsplash.com/photo-1476973422084-e0fa66ff9456'),
                                                                        (default, 16, 'https://images.unsplash.com/photo-1519713958759-6254243c4a53'),
                                                                        (default, 17, 'https://images.unsplash.com/photo-1513836279014-a89f7a76ae86'),
                                                                        (default, 18, 'https://images.unsplash.com/photo-1473448912268-2022ce9509d8'),
                                                                        (default, 19, 'https://images.unsplash.com/photo-1516496636080-14fb876e029d'),
                                                                        (default, 20, 'https://images.unsplash.com/photo-1508193638397-1c4234db14d8');

-- Mushroom Images
INSERT INTO shroomshare.shroom_image(id, shroom_id, image_data) VALUES
                                                                    (default, 1, 'https://www.looduskalender.ee/n/sites/default/files/inline-images/090809aa132_0.jpg'),
                                                                    (default, 2, 'https://www.naturephoto-cz.com/photos/maly/manniriisikas-xxx721.jpg'),
                                                                    (default, 3, 'https://rmk.ee/wp-content/uploads/2019/01/1-12_block.jpg'),
                                                                    (default, 4, 'https://www.looduskalender.ee/n/sites/default/files/inline-images/030831aa020.jpg'),
                                                                    (default, 5, 'https://svampguiden.com/assets/img/sg_img/sg_artbilder/agaricus_arvensis_01.jpg'),
                                                                    (default, 6, 'https://s.err.ee/photo/crop/2020/10/06/825157h5e34t24.jpg'),
                                                                    (default, 7, 'https://s.err.ee/photo/crop/2021/07/17/1076517h095at24.jpg'),
                                                                    (default, 8, 'https://nami-nami.ee/files//recipes/1001/veisekeel.jpg'),
                                                                    (default, 9, 'https://www.hansaplant.ee/media/catalog/product/cache/image/1000x1000/e9c3970ab036de70892d86c6d221abfe/1/1/113426.jpg'),
                                                                    (default, 10, 'https://www.loodusfoto.eu/storage/cache/images/000/138/seen22,medium.2x.1676395587.jpg');

-- Comments (2 per location by Jaan and Liisa)
INSERT INTO shroomshare.comment(id, location_id, user_id, body, rating, created, status) VALUES
                                                                                             (default, 1, 4, 'Suurepärane mets, palju kukeseeni!', 5, CURRENT_DATE, 'A'),
                                                                                             (default, 1, 5, 'Hea jalutuskäigu koht, kuid kitsas parkimisala.', 4, CURRENT_DATE, 'A'),
                                                                                             (default, 2, 4, 'Raadi mets on ideaalne puraviku jaoks.', 5, CURRENT_DATE, 'A'),
                                                                                             (default, 2, 5, 'Kaunis ja rahulik mets Tartu lähedal.', 4, CURRENT_DATE, 'A'),
                                                                                             (default, 3, 4, 'Valgeranna rand on tore, kuid mõnikord liiga rahvarohke.', 3, CURRENT_DATE, 'A'),
                                                                                             (default, 3, 5, 'Kukeseeni saab palju, eriti hommikuti.', 4, CURRENT_DATE, 'A'),
                                                                                             (default, 4, 4, 'Narva-Jõesuu männimets on väga puhas ja vaikne.', 5, CURRENT_DATE, 'A'),
                                                                                             (default, 4, 5, 'Hea jalutuskohaga mets, mõnus lõhn.', 4, CURRENT_DATE, 'A'),
                                                                                             (default, 5, 4, 'Uuemõisa park on kaunis ja sobib perega külastamiseks.', 4, CURRENT_DATE, 'A'),
                                                                                             (default, 5, 5, 'Mitmekesised seened, kuid mõnikord märg pinnas.', 3, CURRENT_DATE, 'A'),
                                                                                             (default, 6, 4, 'Paala mets on ideaalne segamets seente jaoks.', 5, CURRENT_DATE, 'A'),
                                                                                             (default, 6, 5, 'Järve ääres on mõnus istuda ja nautida loodust.', 4, CURRENT_DATE, 'A'),
                                                                                             (default, 7, 4, 'Vallimäe ümbrus on huvitav, ajaloohõnguline.', 4, CURRENT_DATE, 'A'),
                                                                                             (default, 7, 5, 'Mets on rahulik, sobib seente otsimiseks.', 4, CURRENT_DATE, 'A'),
                                                                                             (default, 8, 4, 'Smuuli mets on suurepärane Saaremaal.', 5, CURRENT_DATE, 'A'),
                                                                                             (default, 8, 5, 'Palju erinevaid seeni, kindlasti tagasi tulen.', 5, CURRENT_DATE, 'A'),
                                                                                             (default, 9, 4, 'Tamula järve ümbrus on niiske, sobib pilvikute jaoks.', 4, CURRENT_DATE, 'A'),
                                                                                             (default, 9, 5, 'Kaunis koht piknikuks ja seente kogumiseks.', 4, CURRENT_DATE, 'A'),
                                                                                             (default, 10, 4, 'Karilatsi mets on rahulik ja looduskaunis.', 4, CURRENT_DATE, 'A'),
                                                                                             (default, 10, 5, 'Hea paik jalutamiseks, eriti sügisel.', 4, CURRENT_DATE, 'A'),
                                                                                             (default, 11, 4, 'Kesklinna park on kena, eriti varahommikul.', 4, CURRENT_DATE, 'A'),
                                                                                             (default, 11, 5, 'Sobib hästi väikeste lastega jalutamiseks.', 3, CURRENT_DATE, 'A'),
                                                                                             (default, 12, 4, 'Kukruse mets on vaikne ja seenerohke.', 4, CURRENT_DATE, 'A'),
                                                                                             (default, 12, 5, 'Leidsime palju pilvikuid ja puravikke.', 4, CURRENT_DATE, 'A'),
                                                                                             (default, 13, 4, 'Piiriäärne mets on rahulik ja ilus.', 4, CURRENT_DATE, 'A'),
                                                                                             (default, 13, 5, 'Metsas jalutamine mõnus ja turvaline.', 4, CURRENT_DATE, 'A'),
                                                                                             (default, 14, 4, 'Hiiumaa park on kaunis ja avar.', 4, CURRENT_DATE, 'A'),
                                                                                             (default, 14, 5, 'Hea koht piknikuks ja seente otsimiseks.', 3, CURRENT_DATE, 'A'),
                                                                                             (default, 15, 4, 'Verevi järve mets on niiske ja lopsakas.', 5, CURRENT_DATE, 'A'),
                                                                                             (default, 15, 5, 'Leidsime palju puravikke, ilus paik.', 4, CURRENT_DATE, 'A'),
                                                                                             (default, 16, 4, 'Keila Joa park on vana ja kaunis.', 5, CURRENT_DATE, 'A'),
                                                                                             (default, 16, 5, 'Väga ilus jalutuskoht, soovitan külastada.', 4, CURRENT_DATE, 'A'),
                                                                                             (default, 17, 4, 'Vilsandi lähedal on meremets kaunis ja rahulik.', 4, CURRENT_DATE, 'A'),
                                                                                             (default, 17, 5, 'Ideaalne koht loodusesse sukeldumiseks.', 4, CURRENT_DATE, 'A'),
                                                                                             (default, 18, 4, 'Mahtra mets on keskmise suurusega, hea segamets.', 4, CURRENT_DATE, 'A'),
                                                                                             (default, 18, 5, 'Sobib hästi seenelkäiguks.', 3, CURRENT_DATE, 'A'),
                                                                                             (default, 19, 4, 'Pedja jõe äär on niiske ja seenerohke.', 5, CURRENT_DATE, 'A'),
                                                                                             (default, 19, 5, 'Rahulik ja ilus jõeäärne mets.', 4, CURRENT_DATE, 'A'),
                                                                                             (default, 20, 4, 'Tapa raudteejaama mets on vaikne ja sobib hommikuseks jalutuseks.', 3, CURRENT_DATE, 'A'),
                                                                                             (default, 20, 5, 'Mets on kena, kuid piiratud parkimisvõimalused.', 3, CURRENT_DATE, 'A');
