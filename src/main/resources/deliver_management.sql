-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Feb 05, 2024 at 08:55 AM
-- Server version: 8.0.30
-- PHP Version: 8.2.8

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `deliver_management`
--

-- --------------------------------------------------------

--
-- Table structure for table `orders`
--

CREATE TABLE `orders` (
  `id` int NOT NULL,
  `order_number` varchar(255) NOT NULL,
  `delivery_address` varchar(255) DEFAULT NULL,
  `delivery_date` datetime DEFAULT NULL,
  `order_status` varchar(255) DEFAULT 'pending',
  `order_assigned` tinyint(1) NOT NULL DEFAULT '0',
  `user_id` int NOT NULL,
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `orders`
--

INSERT INTO `orders` (`id`, `order_number`, `delivery_address`, `delivery_date`, `order_status`, `order_assigned`, `user_id`, `created_at`) VALUES
(5, 'ce39296d', 'The name is namee', '2024-01-28 16:39:45', 'delivered', 1, 1, '2024-01-19 16:40:31'),
(6, '95773b21', 'olatunbsoun tetst', '2024-01-25 17:03:04', 'delivered', 1, 1, '2024-01-19 17:03:06'),
(7, '6253e5a7', 'This is me testing', '2024-01-31 17:04:07', 'delivered', 1, 1, '2024-01-19 17:04:10'),
(8, '272df95a', 'ahjhshjhjas', '2024-01-27 17:06:59', 'assigned_to_driver', 1, 1, '2024-01-19 17:07:01'),
(9, 'koffi', '4, Saint etienne jkwjk', '2024-02-08 13:13:28', 'assigned_to_driver', 1, 1, '2024-02-02 13:13:31'),
(10, '1\0\0\02', 'ewewwe', '2024-02-06 09:49:16', 'order_booked', 0, 6, '2024-02-04 09:50:13'),
(11, 'h7ljc', 'Testing', '2024-02-06 11:37:08', 'delivered', 1, 7, '2024-02-04 11:37:19'),
(12, '370e1', 'testing this with ', '2024-02-08 11:42:47', 'assigned_to_driver', 1, 8, '2024-02-04 11:42:56'),
(13, '1dbz6', 'Thississd', '2024-02-07 09:13:36', 'delivered', 1, 9, '2024-02-05 09:14:06');

-- --------------------------------------------------------

--
-- Table structure for table `order_delivery`
--

CREATE TABLE `order_delivery` (
  `id` int NOT NULL,
  `order_id` int NOT NULL,
  `driver_id` int NOT NULL,
  `sequence` int NOT NULL,
  `location` varchar(255) NOT NULL,
  `order_status` varchar(255) NOT NULL,
  `created_at` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `order_delivery`
--

INSERT INTO `order_delivery` (`id`, `order_id`, `driver_id`, `sequence`, `location`, `order_status`, `created_at`) VALUES
(1, 7, 2, 1, 'This is me testing', 'delivered', '2024-02-02'),
(2, 6, 2, 2, 'olatunbsoun tetst', 'delivered', '2024-02-02'),
(3, 5, 2, 3, 'The name is namee', 'delivered', '2024-02-02'),
(9, 8, 5, 1, 'ahjhshjhjas', 'assigned_to_driver', '2024-02-02'),
(10, 9, 5, 1, '4, Saint etienne jkwjk', 'assigned_to_driver', '2024-02-02'),
(11, 11, 2, 1, 'Testing', 'delivered', '2024-02-04'),
(12, 12, 5, 1, 'testing this with ', 'assigned_to_driver', '2024-02-04'),
(13, 13, 2, 1, 'Thississd', 'delivered', '2024-02-05');

-- --------------------------------------------------------

--
-- Table structure for table `order_items`
--

CREATE TABLE `order_items` (
  `id` int NOT NULL,
  `order_id` int NOT NULL,
  `product_id` int NOT NULL,
  `quantity` int NOT NULL,
  `date_created` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `order_items`
--

INSERT INTO `order_items` (`id`, `order_id`, `product_id`, `quantity`, `date_created`) VALUES
(1, 5, 2, 1, '2024-01-19 16:40:31'),
(2, 6, 1, 5, '2024-01-19 17:03:06'),
(3, 7, 2, 6, '2024-01-19 17:04:10'),
(4, 8, 2, 10, '2024-01-19 17:07:01'),
(5, 9, 1, 2, '2024-02-02 13:13:31'),
(6, 11, 2, 3, '2024-02-04 11:37:19'),
(7, 12, 1, 20, '2024-02-04 11:42:56'),
(8, 13, 1, 20, '2024-02-05 09:14:06');

-- --------------------------------------------------------

--
-- Table structure for table `products`
--

CREATE TABLE `products` (
  `id` int NOT NULL,
  `product_name` varchar(255) NOT NULL,
  `date_created` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `products`
--

INSERT INTO `products` (`id`, `product_name`, `date_created`) VALUES
(1, 'Rice', '2024-01-19 14:22:58'),
(2, 'Beans', '2024-01-19 14:25:00'),
(3, 'Garri', '2024-02-04 15:06:50'),
(4, 'Fromage', '2024-02-04 15:06:58');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` int NOT NULL,
  `fullname` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `phone_number` varchar(255) NOT NULL,
  `role` enum('customer','driver','scheduler') CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `truck_number` varchar(244) DEFAULT NULL,
  `truck_capacity` varchar(244) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `date_created` timestamp NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `fullname`, `email`, `password`, `phone_number`, `role`, `truck_number`, `truck_capacity`, `date_created`) VALUES
(1, 'bosun olatunbsosun', 'olatunbosun@gmail.com', '$2a$10$TqAP3.mJAHwIBpsLDoGfnOj.aPTs69SlYw.E1Y9pdhlHgel34euiK', '0909137234', 'customer', 'null', 'null', '2024-01-16 13:21:45'),
(2, 'Driver Main updated', 'olatunbosundriver222@gmail.com', '$2a$10$vFUYN6buGvu82arN.Jx1Z.xjJ.PFHtWaxwEzTQvwB67SqefBxLaUC', '09091826353', 'driver', '9377466344', '1500kgkg', '2024-01-16 13:33:02'),
(3, 'ola schedular', 'olatunbosun+1@gmail.com', '$2a$10$qjmq9fWdwQGizDXFgD.3v.MGxJQrbSq8TJ.1WwUYukxAyx14y1a1.', '09091984246', 'scheduler', 'null', 'nullkg', '2024-01-19 13:16:08'),
(4, 'Olatunbosun', 'olatunbosun@gmail.com', '$2a$10$NNCN3b/4ceh8K1fHqLMq0e1mEzJkWr2V6pYRvq4z1YeW60O2hKL0W', '09091984245', 'customer', 'null', 'nullkg', '2024-02-02 16:01:42'),
(5, 'Driver new testing', 'drivertesting@gmail.com', '$2a$10$CXPcRxPgIcQGXQDs1Du/XOXRDTqYjk2t4Kpj9mYbL0CfigT/C15TO', '090919826352', 'driver', '234372663', '3000kg', '2024-02-03 12:08:30'),
(6, 'bosun olatunbsosun', 'olatunbosun1@gmail.com', 'Tunbosun1998@', '0909137234e', 'customer', '', '', '2024-02-04 07:51:12'),
(7, 'My name', 'testing@gmail.com', '$2a$10$G..y9XHHKz80bS2YHEENeuGrOT0b0hcZOv4eh1bza4Yxkf9JccBla', '09090123433', 'customer', '', 'kg', '2024-02-04 10:36:10'),
(8, ' John doe', 'testte@gmail.com', '$2a$10$yO2F8i4j8OvFaYnDEC/6tOLkNnZ3bbCfuDHsvoZRi.E/FgY.5Sdpa', '090927632', 'customer', '', 'kg', '2024-02-04 10:41:48'),
(9, 'test customer updated', 'testcustomer@gmail.com', '$2a$10$9uZ.DbkWtPkDsGA8JR4k4uHjwW84to01qP3PJrdXhqkImmLLi7EAG', '0909276532', 'customer', '', 'kg', '2024-02-05 08:12:07');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `orders`
--
ALTER TABLE `orders`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_orders_user_id` (`user_id`);

--
-- Indexes for table `order_delivery`
--
ALTER TABLE `order_delivery`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `order_items`
--
ALTER TABLE `order_items`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_order_items_order_id` (`order_id`),
  ADD KEY `fk_order_items_product_id` (`product_id`);

--
-- Indexes for table `products`
--
ALTER TABLE `products`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `orders`
--
ALTER TABLE `orders`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT for table `order_delivery`
--
ALTER TABLE `order_delivery`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT for table `order_items`
--
ALTER TABLE `order_items`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `products`
--
ALTER TABLE `products`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `orders`
--
ALTER TABLE `orders`
  ADD CONSTRAINT `fk_orders_user_id` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE RESTRICT;

--
-- Constraints for table `order_items`
--
ALTER TABLE `order_items`
  ADD CONSTRAINT `fk_order_items_order_id` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`) ON DELETE RESTRICT,
  ADD CONSTRAINT `fk_order_items_product_id` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`) ON DELETE RESTRICT;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
