-- Insertar Establecimientos
INSERT INTO establecimiento (nombre, descripcion, ubicacion, puntuacion) VALUES
('Bar Lechuga', 'Bar tradicional con los mejores desayunos', 'Calle Principal 10, Sevilla', 4.5),
('Peña', 'Ambiente acogedor y desayunos caseros', 'Plaza Mayor 5, El Viso', 4.2),
('Tantra', 'Cafetería moderna con toque andaluz', 'Avenida de la Constitución 15, Sevilla', 4.7),
('La Rocambolesca', 'Desayunos creativos y tradicionales', 'Calle Real 20, Mairena', 4.6);

-- Insertar Desayunos
INSERT INTO desayuno (id_establecimiento, nombre, precio, imagen, puntuacion) VALUES
(1, 'Tostá con jamón', 3.50, 'tosta_jamon.jpg', 4.8),
(1, 'Tostá de pringá', 3.00, 'tosta_pringa.jpg', 4.6),
(2, 'Mollete con aceite y tomate', 2.50, 'mollete_aceite_tomate.jpg', 4.3),
(2, 'Pan con manteca colorá', 2.80, 'pan_manteca_colora.jpg', 4.5),
(3, 'Pitufo mixto', 3.20, 'pitufo_mixto.jpg', 4.4),
(4, 'Churros con chocolate', 4.00, 'churros_chocolate.jpg', 4.7);

-- Insertar Usuarios
INSERT INTO usuario (username, email, password) VALUES
('maria_garcia', 'maria.garcia@email.com', 'password123'),
('juan_lopez', 'juan.lopez@email.com', 'securepass456'),
('ana_martinez', 'ana.martinez@email.com', 'mypassword789'),
('carlos_rodriguez', 'carlos.rodriguez@email.com', 'strongpass321'),
('laura_fernandez', 'laura.fernandez@email.com', 'safepassword654');

-- Insertar Reviews
INSERT INTO review (id_usuario, id_desayuno, fecha, precio, imagen, puntuacion, comentarios) VALUES
(1, 1, '2025-03-01 09:30:00', 3.50, 'review_tosta_jamon.jpg', 5, 'La mejor tostá con jamón que he probado. El pan estaba crujiente y el jamón de primera calidad.'),
(2, 2, '2025-03-02 08:45:00', 3.00, 'review_tosta_pringa.jpg', 4, 'La tostá de pringá estaba deliciosa, aunque un poco grasienta para mi gusto.'),
(3, 3, '2025-03-03 10:15:00', 2.50, 'review_mollete.jpg', 5, 'El mollete con aceite y tomate es un clásico que nunca falla. Fresco y sabroso.'),
(4, 4, '2025-03-04 09:00:00', 2.80, 'review_manteca_colora.jpg', 4, 'La manteca colorá tenía un sabor intenso y estaba bien especiada. Una delicia.'),
(5, 5, '2025-03-05 08:30:00', 3.20, 'review_pitufo_mixto.jpg', 4, 'El pitufo mixto estaba bien, pero esperaba un poco más de sabor en el jamón.'),
(1, 6, '2025-03-06 11:00:00', 4.00, 'review_churros.jpg', 5, 'Los churros estaban crujientes y el chocolate espeso y cremoso. Perfecto para un día frío.'),
(2, 1, '2025-03-06 09:45:00', 3.50, 'review_tosta_jamon2.jpg', 5, 'Increíble calidad del jamón. La tostada estaba en su punto, crujiente por fuera y tierna por dentro.'),
(3, 2, '2025-03-07 10:30:00', 3.00, 'review_tosta_pringa2.jpg', 4, 'La pringá estaba sabrosa, aunque un poco seca. El pan compensaba bien.'),
(4, 3, '2025-03-07 08:15:00', 2.50, 'review_mollete2.jpg', 5, 'El aceite era de primera calidad y el tomate muy fresco. Un desayuno simple pero delicioso.'),
(5, 4, '2025-03-08 09:30:00', 2.80, 'review_manteca_colora2.jpg', 5, 'La manteca colorá estaba perfectamente especiada. Un sabor auténtico de Andalucía.');