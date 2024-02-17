-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Εξυπηρετητής: 127.0.0.1
-- Χρόνος δημιουργίας: 17 Φεβ 2024 στις 04:19:18
-- Έκδοση διακομιστή: 10.4.28-MariaDB
-- Έκδοση PHP: 8.1.17

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Βάση δεδομένων: `dbrentcarapp`
--

-- --------------------------------------------------------

--
-- Δομή πίνακα για τον πίνακα `car`
--

CREATE TABLE `car` (
  `car_id` int(11) NOT NULL,
  `category_id` int(11) DEFAULT NULL,
  `model` varchar(100) NOT NULL,
  `daily_cost` decimal(10,2) NOT NULL,
  `cubic_capacity` int(11) DEFAULT NULL,
  `seat` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Άδειασμα δεδομένων του πίνακα `car`
--

INSERT INTO `car` (`car_id`, `category_id`, `model`, `daily_cost`, `cubic_capacity`, `seat`) VALUES
(1, 1, 'Toyota Yaris', 30.00, 1200, 5),
(2, 2, 'Ford Mustang', 70.00, 3500, 4),
(3, 3, 'Volkswagen Golf', 40.00, 1600, 5),
(4, 4, 'Jeep Wrangler', 80.00, 4000, 4),
(7, 2, 'Alfa Romeo', 50.00, 1400, 4);

-- --------------------------------------------------------

--
-- Δομή πίνακα για τον πίνακα `carcategory`
--

CREATE TABLE `carcategory` (
  `category_id` int(11) NOT NULL,
  `name` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Άδειασμα δεδομένων του πίνακα `carcategory`
--

INSERT INTO `carcategory` (`category_id`, `name`) VALUES
(1, 'Small'),
(2, 'Large'),
(3, 'Economy'),
(4, 'Jeep');

-- --------------------------------------------------------

--
-- Δομή πίνακα για τον πίνακα `customer`
--

CREATE TABLE `customer` (
  `customer_id` int(11) NOT NULL,
  `first_name` varchar(50) NOT NULL,
  `last_name` varchar(50) NOT NULL,
  `gender` enum('Male','Female','Other') NOT NULL,
  `home_address` varchar(255) NOT NULL,
  `email` varchar(100) NOT NULL,
  `phone` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Άδειασμα δεδομένων του πίνακα `customer`
--

INSERT INTO `customer` (`customer_id`, `first_name`, `last_name`, `gender`, `home_address`, `email`, `phone`) VALUES
(1, 'Mixalis', 'Tarasov', 'Male', 'Foinikos 8, Thessaloniki', 'mixtara@gmail.com', '6986986986'),
(2, 'Xristos', 'Tara', 'Female', 'Mpotsari 28, Thessaloniki', 'xristara@gmail.com', '1234567891'),
(5, 'mix', 'tar', 'Male', 'papafi 9', 'mixtar@gmail.com', '599452959');

-- --------------------------------------------------------

--
-- Δομή πίνακα για τον πίνακα `rental`
--

CREATE TABLE `rental` (
  `rental_id` int(11) NOT NULL,
  `customer_id` int(11) DEFAULT NULL,
  `car_id` int(11) DEFAULT NULL,
  `rental_days` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Άδειασμα δεδομένων του πίνακα `rental`
--

INSERT INTO `rental` (`rental_id`, `customer_id`, `car_id`, `rental_days`) VALUES
(1, 1, 1, 5),
(2, 2, 3, 3),
(3, 1, 4, 7),
(6, 1, 7, 7);

--
-- Ευρετήρια για άχρηστους πίνακες
--

--
-- Ευρετήρια για πίνακα `car`
--
ALTER TABLE `car`
  ADD PRIMARY KEY (`car_id`),
  ADD KEY `category_id` (`category_id`);

--
-- Ευρετήρια για πίνακα `carcategory`
--
ALTER TABLE `carcategory`
  ADD PRIMARY KEY (`category_id`);

--
-- Ευρετήρια για πίνακα `customer`
--
ALTER TABLE `customer`
  ADD PRIMARY KEY (`customer_id`);

--
-- Ευρετήρια για πίνακα `rental`
--
ALTER TABLE `rental`
  ADD PRIMARY KEY (`rental_id`),
  ADD KEY `customer_id` (`customer_id`),
  ADD KEY `car_id` (`car_id`);

--
-- AUTO_INCREMENT για άχρηστους πίνακες
--

--
-- AUTO_INCREMENT για πίνακα `car`
--
ALTER TABLE `car`
  MODIFY `car_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT για πίνακα `carcategory`
--
ALTER TABLE `carcategory`
  MODIFY `category_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT για πίνακα `customer`
--
ALTER TABLE `customer`
  MODIFY `customer_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT για πίνακα `rental`
--
ALTER TABLE `rental`
  MODIFY `rental_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- Περιορισμοί για άχρηστους πίνακες
--

--
-- Περιορισμοί για πίνακα `car`
--
ALTER TABLE `car`
  ADD CONSTRAINT `car_ibfk_1` FOREIGN KEY (`category_id`) REFERENCES `carcategory` (`category_id`) ON DELETE CASCADE;

--
-- Περιορισμοί για πίνακα `rental`
--
ALTER TABLE `rental`
  ADD CONSTRAINT `rental_ibfk_1` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`customer_id`) ON DELETE CASCADE,
  ADD CONSTRAINT `rental_ibfk_2` FOREIGN KEY (`car_id`) REFERENCES `car` (`car_id`) ON DELETE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
