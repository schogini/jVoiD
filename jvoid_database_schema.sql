-- phpMyAdmin SQL Dump
-- version 4.2.11
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Feb 04, 2015 at 12:53 PM
-- Server version: 5.6.21
-- PHP Version: 5.6.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `jvoid`
--

-- --------------------------------------------------------

--
-- Table structure for table `attribute`
--

DROP TABLE IF EXISTS `attribute`;
CREATE TABLE IF NOT EXISTS `attribute` (
`id` int(11) NOT NULL,
  `attribute_group_id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `attribute`
--

INSERT INTO `attribute` (`id`, `attribute_group_id`, `name`) VALUES
(1, 1, 'Size');

-- --------------------------------------------------------

--
-- Table structure for table `attribute_group`
--

DROP TABLE IF EXISTS `attribute_group`;
CREATE TABLE IF NOT EXISTS `attribute_group` (
`id` int(11) NOT NULL,
  `attribute_set_id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `attribute_group`
--

INSERT INTO `attribute_group` (`id`, `attribute_set_id`, `name`) VALUES
(1, 1, 'MyGroup123');

-- --------------------------------------------------------

--
-- Table structure for table `attribute_set`
--

DROP TABLE IF EXISTS `attribute_set`;
CREATE TABLE IF NOT EXISTS `attribute_set` (
`id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `attribute_values`
--

DROP TABLE IF EXISTS `attribute_values`;
CREATE TABLE IF NOT EXISTS `attribute_values` (
`id` int(11) NOT NULL,
  `attribute_id` int(11) NOT NULL,
  `value` varchar(255) NOT NULL,
  `language` varchar(10) NOT NULL,
  `position` int(11) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `attribute_values`
--

INSERT INTO `attribute_values` (`id`, `attribute_id`, `value`, `language`, `position`) VALUES
(2, 1, '7test', 'enUS', 2),
(3, 1, '8test', 'enUS', 3);

-- --------------------------------------------------------

--
-- Table structure for table `categories`
--

DROP TABLE IF EXISTS `categories`;
CREATE TABLE IF NOT EXISTS `categories` (
`id` int(11) NOT NULL,
  `parent_id` int(11) NOT NULL,
  `created_on` datetime NOT NULL,
  `updated_on` datetime NOT NULL,
  `level` int(11) NOT NULL,
  `position` int(11) NOT NULL,
  `children_count` int(11) NOT NULL,
  `path` varchar(255) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `categories`
--

INSERT INTO `categories` (`id`, `parent_id`, `created_on`, `updated_on`, `level`, `position`, `children_count`, `path`) VALUES
(1, 0, '2015-01-06 00:00:00', '2015-01-22 12:09:00', 1, 1, 2, '1/'),
(2, 1, '2015-01-14 00:00:00', '2015-01-13 00:00:00', 2, 1, 1, '1/2/'),
(3, 1, '2015-01-22 12:24:02', '2015-01-22 03:27:21', 2, 2, 0, '1/3/'),
(4, 0, '2015-01-23 12:26:16', '2015-01-23 12:26:16', 1, 1, 1, '4/'),
(5, 4, '2015-02-01 00:00:00', '2015-02-02 00:00:00', 2, 1, 0, '4/5');

-- --------------------------------------------------------

--
-- Table structure for table `category_entity_values`
--

DROP TABLE IF EXISTS `category_entity_values`;
CREATE TABLE IF NOT EXISTS `category_entity_values` (
`id` int(11) NOT NULL,
  `category_id` int(11) NOT NULL,
  `attribute_id` int(11) NOT NULL,
  `language` varchar(10) NOT NULL,
  `value` text NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `category_entity_values`
--

INSERT INTO `category_entity_values` (`id`, `category_id`, `attribute_id`, `language`, `value`) VALUES
(1, 1, 39, 'enUS', 'Books'),
(2, 2, 39, 'enUS', 'Fiction'),
(3, 3, 39, 'enUS', 'Non-fiction'),
(4, 4, 39, 'enUS', 'Electronics'),
(5, 5, 39, 'enUS', 'Mobile');

-- --------------------------------------------------------

--
-- Table structure for table `category_products`
--

DROP TABLE IF EXISTS `category_products`;
CREATE TABLE IF NOT EXISTS `category_products` (
`id` int(11) NOT NULL,
  `category_id` int(11) NOT NULL,
  `product_id` int(11) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `category_products`
--

INSERT INTO `category_products` (`id`, `category_id`, `product_id`) VALUES
(1, 2, 1),
(2, 2, 2),
(3, 2, 3),
(4, 2, 4),
(5, 3, 5),
(6, 4, 6),
(7, 5, 7),
(8, 5, 8);

-- --------------------------------------------------------

--
-- Table structure for table `checkout_order`
--

DROP TABLE IF EXISTS `checkout_order`;
CREATE TABLE IF NOT EXISTS `checkout_order` (
`id` int(10) unsigned NOT NULL COMMENT 'Id',
  `store_id` smallint(5) unsigned NOT NULL DEFAULT '0' COMMENT 'Store Id',
  `created_at` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT 'Created At',
  `updated_at` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT 'Updated At',
  `is_active` smallint(5) unsigned DEFAULT '1' COMMENT 'Is Active',
  `is_virtual` smallint(5) unsigned DEFAULT '0' COMMENT 'Is Virtual',
  `is_multi_shipping` smallint(5) unsigned DEFAULT '0' COMMENT 'Is Multi Shipping',
  `status` varchar(255) DEFAULT NULL COMMENT 'Order Status',
  `items_count` int(10) unsigned DEFAULT '0' COMMENT 'Items Count',
  `items_quantity` decimal(12,4) DEFAULT '0.0000' COMMENT 'Items Quantity',
  `grand_total` decimal(12,4) DEFAULT '0.0000' COMMENT 'Grand Total',
  `base_grand_total` decimal(12,4) DEFAULT '0.0000' COMMENT 'Base Grand Total',
  `checkout_method` varchar(255) DEFAULT NULL COMMENT 'Checkout Method',
  `checkout_comment` varchar(255) DEFAULT NULL,
  `customer_id` int(10) unsigned DEFAULT '0' COMMENT 'Customer Id',
  `customer_group_id` int(10) unsigned DEFAULT '0' COMMENT 'Customer Group Id',
  `customer_email` varchar(255) DEFAULT NULL COMMENT 'Customer Email',
  `customer_prefix` varchar(40) DEFAULT NULL COMMENT 'Customer Prefix',
  `customer_firstname` varchar(255) DEFAULT NULL COMMENT 'Customer Firstname',
  `customer_middlename` varchar(40) DEFAULT NULL COMMENT 'Customer Middlename',
  `customer_lastname` varchar(255) DEFAULT NULL COMMENT 'Customer Lastname',
  `customer_suffix` varchar(40) DEFAULT NULL COMMENT 'Customer Suffix',
  `customer_dob` datetime DEFAULT NULL COMMENT 'Customer Dob',
  `customer_is_guest` smallint(5) unsigned DEFAULT '0' COMMENT 'Customer Is Guest',
  `remote_ip` varchar(32) DEFAULT NULL COMMENT 'Remote Ip',
  `customer_gender` varchar(255) DEFAULT NULL COMMENT 'Customer Gender',
  `subtotal` decimal(12,4) DEFAULT NULL COMMENT 'Subtotal',
  `base_subtotal` decimal(12,4) DEFAULT NULL COMMENT 'Base Subtotal',
  `is_changed` int(10) unsigned DEFAULT NULL COMMENT 'Is Changed'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Checkout Order';

-- --------------------------------------------------------

--
-- Table structure for table `checkout_order_address`
--

DROP TABLE IF EXISTS `checkout_order_address`;
CREATE TABLE IF NOT EXISTS `checkout_order_address` (
`id` int(10) unsigned NOT NULL COMMENT 'Checkout Order Address Id',
  `order_id` int(10) unsigned DEFAULT NULL COMMENT 'Order Id',
  `created_at` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT 'Created At',
  `updated_at` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT 'Updated At',
  `customer_address_id` int(11) DEFAULT NULL COMMENT 'Customer Address Id',
  `quote_address_id` int(11) DEFAULT NULL COMMENT 'Quote Address Id',
  `region_id` int(11) DEFAULT NULL COMMENT 'Region Id',
  `customer_id` int(11) DEFAULT NULL COMMENT 'Customer Id',
  `fax` varchar(255) DEFAULT NULL COMMENT 'Fax',
  `region` varchar(255) DEFAULT NULL COMMENT 'Region',
  `postcode` varchar(255) DEFAULT NULL COMMENT 'Postcode',
  `lastname` varchar(255) DEFAULT NULL COMMENT 'Lastname',
  `street` varchar(255) DEFAULT NULL COMMENT 'Street',
  `city` varchar(255) DEFAULT NULL COMMENT 'City',
  `email` varchar(255) DEFAULT NULL COMMENT 'Email',
  `telephone` varchar(255) DEFAULT NULL COMMENT 'Telephone',
  `country_id` varchar(255) DEFAULT NULL COMMENT 'Country Id',
  `firstname` varchar(255) DEFAULT NULL COMMENT 'Firstname',
  `address_type` varchar(255) DEFAULT NULL COMMENT 'Address Type',
  `prefix` varchar(255) DEFAULT NULL COMMENT 'Prefix',
  `middlename` varchar(255) DEFAULT NULL COMMENT 'Middlename',
  `suffix` varchar(255) DEFAULT NULL COMMENT 'Suffix',
  `company` varchar(255) DEFAULT NULL COMMENT 'Company'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Checkout Order Address';

-- --------------------------------------------------------

--
-- Table structure for table `checkout_order_item`
--

DROP TABLE IF EXISTS `checkout_order_item`;
CREATE TABLE IF NOT EXISTS `checkout_order_item` (
`id` int(10) unsigned NOT NULL COMMENT 'Order Item Id',
  `order_id` int(10) unsigned NOT NULL DEFAULT '0' COMMENT 'Order Id',
  `created_at` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT 'Created At',
  `updated_at` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT 'Updated At',
  `product_id` int(10) unsigned DEFAULT NULL COMMENT 'Product Id',
  `attribute_id` int(10) unsigned DEFAULT NULL COMMENT 'Attribute Id',
  `store_id` smallint(5) unsigned DEFAULT NULL COMMENT 'Store Id',
  `parent_id` int(10) unsigned DEFAULT NULL COMMENT 'Parent Item Id',
  `is_virtual` smallint(5) unsigned DEFAULT NULL COMMENT 'Is Virtual',
  `sku` varchar(255) DEFAULT NULL COMMENT 'Sku',
  `name` varchar(255) DEFAULT NULL COMMENT 'Name',
  `description` longtext COMMENT 'Description',
  `free_shipping` smallint(5) unsigned DEFAULT '0' COMMENT 'Free Shipping',
  `weight` decimal(12,4) DEFAULT '0.0000' COMMENT 'Weight',
  `quantity` decimal(12,4) DEFAULT '0.0000' COMMENT 'Quantity',
  `price` decimal(12,4) DEFAULT '0.0000' COMMENT 'Price',
  `base_price` decimal(12,4) DEFAULT '0.0000' COMMENT 'Base Price',
  `row_total` decimal(12,4) DEFAULT '0.0000' COMMENT 'Row Total',
  `base_row_total` decimal(12,4) DEFAULT '0.0000' COMMENT 'Base Row Total',
  `row_weight` decimal(12,4) DEFAULT '0.0000' COMMENT 'Row Weight',
  `product_type` varchar(255) DEFAULT NULL COMMENT 'Product Type'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Checkout Order Item';

-- --------------------------------------------------------

--
-- Table structure for table `checkout_payment_methods`
--

DROP TABLE IF EXISTS `checkout_payment_methods`;
CREATE TABLE IF NOT EXISTS `checkout_payment_methods` (
`id` int(10) unsigned NOT NULL COMMENT 'Payment Method Id',
  `payment_method` varchar(255) DEFAULT NULL COMMENT 'Payment Method',
  `description` varchar(255) DEFAULT NULL COMMENT 'Payment Method Description'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Checkout Payment Methods';

-- --------------------------------------------------------

--
-- Table structure for table `checkout_quote`
--

DROP TABLE IF EXISTS `checkout_quote`;
CREATE TABLE IF NOT EXISTS `checkout_quote` (
`id` int(10) unsigned NOT NULL COMMENT 'Id',
  `store_id` smallint(5) unsigned NOT NULL DEFAULT '0' COMMENT 'Store Id',
  `created_at` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT 'Created At',
  `updated_at` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT 'Updated At',
  `is_active` smallint(5) unsigned DEFAULT '1' COMMENT 'Is Active',
  `is_virtual` smallint(5) unsigned DEFAULT '0' COMMENT 'Is Virtual',
  `is_multi_shipping` smallint(5) unsigned DEFAULT '0' COMMENT 'Is Multi Shipping',
  `items_count` int(10) unsigned DEFAULT '0' COMMENT 'Items Count',
  `items_quantity` decimal(12,4) DEFAULT '0.0000' COMMENT 'Items Quantity',
  `grand_total` decimal(12,4) DEFAULT '0.0000' COMMENT 'Grand Total',
  `base_grand_total` decimal(12,4) DEFAULT '0.0000' COMMENT 'Base Grand Total',
  `checkout_method` varchar(255) DEFAULT NULL COMMENT 'Checkout Method',
  `customer_id` int(10) unsigned DEFAULT '0' COMMENT 'Customer Id',
  `customer_group_id` int(10) unsigned DEFAULT '0' COMMENT 'Customer Group Id',
  `customer_email` varchar(255) DEFAULT NULL COMMENT 'Customer Email',
  `customer_prefix` varchar(40) DEFAULT NULL COMMENT 'Customer Prefix',
  `customer_firstname` varchar(255) DEFAULT NULL COMMENT 'Customer Firstname',
  `customer_middlename` varchar(40) DEFAULT NULL COMMENT 'Customer Middlename',
  `customer_lastname` varchar(255) DEFAULT NULL COMMENT 'Customer Lastname',
  `customer_suffix` varchar(40) DEFAULT NULL COMMENT 'Customer Suffix',
  `customer_dob` datetime DEFAULT NULL COMMENT 'Customer Dob',
  `customer_is_guest` smallint(5) unsigned DEFAULT '0' COMMENT 'Customer Is Guest',
  `remote_ip` varchar(32) DEFAULT NULL COMMENT 'Remote Ip',
  `customer_gender` varchar(255) DEFAULT NULL COMMENT 'Customer Gender',
  `subtotal` decimal(12,4) DEFAULT NULL COMMENT 'Subtotal',
  `base_subtotal` decimal(12,4) DEFAULT NULL COMMENT 'Base Subtotal',
  `is_changed` int(10) unsigned DEFAULT NULL COMMENT 'Is Changed'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Checkout Quote';

-- --------------------------------------------------------

--
-- Table structure for table `checkout_quote_address`
--

DROP TABLE IF EXISTS `checkout_quote_address`;
CREATE TABLE IF NOT EXISTS `checkout_quote_address` (
`id` int(10) unsigned NOT NULL COMMENT 'Checkout Quote Address Id',
  `quote_id` int(10) unsigned DEFAULT NULL COMMENT 'Quote Id',
  `created_at` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT 'Created At',
  `updated_at` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT 'Updated At',
  `customer_address_id` int(11) DEFAULT NULL COMMENT 'Customer Address Id',
  `region_id` int(11) DEFAULT NULL COMMENT 'Region Id',
  `customer_id` int(11) DEFAULT NULL COMMENT 'Customer Id',
  `fax` varchar(255) DEFAULT NULL COMMENT 'Fax',
  `region` varchar(255) DEFAULT NULL COMMENT 'Region',
  `postcode` varchar(255) DEFAULT NULL COMMENT 'Postcode',
  `lastname` varchar(255) DEFAULT NULL COMMENT 'Lastname',
  `street` varchar(255) DEFAULT NULL COMMENT 'Street',
  `city` varchar(255) DEFAULT NULL COMMENT 'City',
  `email` varchar(255) DEFAULT NULL COMMENT 'Email',
  `telephone` varchar(255) DEFAULT NULL COMMENT 'Telephone',
  `country_id` varchar(255) DEFAULT NULL COMMENT 'Country Id',
  `firstname` varchar(255) DEFAULT NULL COMMENT 'Firstname',
  `address_type` varchar(255) DEFAULT NULL COMMENT 'Address Type',
  `prefix` varchar(255) DEFAULT NULL COMMENT 'Prefix',
  `middlename` varchar(255) DEFAULT NULL COMMENT 'Middlename',
  `suffix` varchar(255) DEFAULT NULL COMMENT 'Suffix',
  `company` varchar(255) DEFAULT NULL COMMENT 'Company'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Checkout Quote Address';

-- --------------------------------------------------------

--
-- Table structure for table `checkout_quote_item`
--

DROP TABLE IF EXISTS `checkout_quote_item`;
CREATE TABLE IF NOT EXISTS `checkout_quote_item` (
`id` int(10) unsigned NOT NULL COMMENT 'Item Id',
  `quote_id` int(10) unsigned NOT NULL DEFAULT '0' COMMENT 'Quote Id',
  `created_at` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT 'Created At',
  `updated_at` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT 'Updated At',
  `product_id` int(10) unsigned DEFAULT NULL COMMENT 'Product Id',
  `attribute_id` int(10) unsigned DEFAULT NULL COMMENT 'Attribute Id',
  `store_id` smallint(5) unsigned DEFAULT NULL COMMENT 'Store Id',
  `parent_id` int(10) unsigned DEFAULT NULL COMMENT 'Parent Item Id',
  `is_virtual` smallint(5) unsigned DEFAULT NULL COMMENT 'Is Virtual',
  `sku` varchar(255) DEFAULT NULL COMMENT 'Sku',
  `name` varchar(255) DEFAULT NULL COMMENT 'Name',
  `description` longtext COMMENT 'Description',
  `free_shipping` smallint(5) unsigned DEFAULT '0' COMMENT 'Free Shipping',
  `weight` decimal(12,4) DEFAULT '0.0000' COMMENT 'Weight',
  `quantity` decimal(12,4) DEFAULT '0.0000' COMMENT 'Quantity',
  `price` decimal(12,4) DEFAULT '0.0000' COMMENT 'Price',
  `base_price` decimal(12,4) DEFAULT '0.0000' COMMENT 'Base Price',
  `row_total` decimal(12,4) DEFAULT '0.0000' COMMENT 'Row Total',
  `base_row_total` decimal(12,4) DEFAULT '0.0000' COMMENT 'Base Row Total',
  `row_weight` decimal(12,4) DEFAULT '0.0000' COMMENT 'Row Weight',
  `product_type` varchar(255) DEFAULT NULL COMMENT 'Product Type'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Checkout Quote Item';

-- --------------------------------------------------------

--
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
CREATE TABLE IF NOT EXISTS `customer` (
`id` int(11) NOT NULL,
  `customer_group_id` int(11) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP,
  `updated_at` datetime DEFAULT NULL,
  `is_acive` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `customer_address`
--

DROP TABLE IF EXISTS `customer_address`;
CREATE TABLE IF NOT EXISTS `customer_address` (
`id` int(11) NOT NULL,
  `customer_id` int(11) NOT NULL,
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `customer_address_attribute_values`
--

DROP TABLE IF EXISTS `customer_address_attribute_values`;
CREATE TABLE IF NOT EXISTS `customer_address_attribute_values` (
`id` int(11) NOT NULL,
  `customer_id` int(11) NOT NULL,
  `attribute_id` int(11) NOT NULL,
  `language` varchar(10) NOT NULL,
  `value` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `customer_entity_values`
--

DROP TABLE IF EXISTS `customer_entity_values`;
CREATE TABLE IF NOT EXISTS `customer_entity_values` (
`id` int(11) NOT NULL,
  `customer_id` int(11) NOT NULL,
  `attribute_id` int(11) NOT NULL,
  `language` varchar(100) NOT NULL,
  `value` text
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `customer_group`
--

DROP TABLE IF EXISTS `customer_group`;
CREATE TABLE IF NOT EXISTS `customer_group` (
`id` int(11) NOT NULL,
  `code` varchar(255) NOT NULL,
  `tax_class_id` int(11) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `customer_group`
--

INSERT INTO `customer_group` (`id`, `code`, `tax_class_id`) VALUES
(1, 'Wholesaler', 1),
(2, 'Retailer', 2),
(3, 'Buyer', 3);

-- --------------------------------------------------------

--
-- Table structure for table `customer_master`
--

DROP TABLE IF EXISTS `customer_master`;
CREATE TABLE IF NOT EXISTS `customer_master` (
`id` int(11) NOT NULL,
  `customer_group` int(11) NOT NULL,
  `prefix` varchar(50) NOT NULL,
  `first_name` varchar(100) NOT NULL,
  `middle_name` varchar(100) NOT NULL,
  `last_name` varchar(100) NOT NULL,
  `email` varchar(255) NOT NULL,
  `date_of_birth` date NOT NULL,
  `tax_number` varchar(100) NOT NULL,
  `gender` varchar(50) NOT NULL,
  `password` varchar(100) NOT NULL,
  `company` varchar(100) NOT NULL,
  `street_address1` varchar(100) NOT NULL,
  `street_address2` varchar(100) NOT NULL,
  `street_address3` varchar(100) NOT NULL,
  `city` varchar(100) NOT NULL,
  `country` varchar(100) NOT NULL,
  `state` varchar(100) NOT NULL,
  `postal_code` varchar(50) NOT NULL,
  `phone` int(50) NOT NULL,
  `fax` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `entities`
--

DROP TABLE IF EXISTS `entities`;
CREATE TABLE IF NOT EXISTS `entities` (
`id` int(11) NOT NULL,
  `code` varchar(50) NOT NULL,
  `type` varchar(50) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `entities`
--

INSERT INTO `entities` (`id`, `code`, `type`) VALUES
(1, 'name', 'Product'),
(2, 'description', 'Product'),
(3, 'shortDescription', 'Product'),
(4, 'sku', 'Product'),
(5, 'weight', 'Product'),
(6, 'fromDate', 'Product'),
(7, 'toDate', 'Product'),
(8, 'status', 'Product'),
(9, 'urlKey', 'Product'),
(10, 'visibility', 'Product'),
(11, 'country', 'Product'),
(12, 'price', 'Product'),
(13, 'image', 'Product'),
(14, 'qty', 'Product'),
(15, 'stock', 'Product'),
(16, 'type', 'Product'),
(17, 'hasMoreOption', 'Product'),
(18, 'requiredOption', 'Product'),
(19, 'customerGroup', 'Customer'),
(20, 'prefix', 'Customer'),
(21, 'firstName', 'Customer'),
(22, 'middleName', 'Customer'),
(23, 'lastName', 'Customer'),
(24, 'email', 'Customer'),
(25, 'dateOfBirth', 'Customer'),
(26, 'taxNumber', 'Customer'),
(27, 'gender', 'Customer'),
(28, 'password', 'Customer'),
(29, 'company', 'Customer'),
(30, 'streetAddress1', 'Customer'),
(31, 'streetAddress2', 'Customer'),
(32, 'streetAddress3', 'Customer'),
(33, 'city', 'Customer'),
(34, 'country', 'Customer'),
(35, 'state', 'Customer'),
(36, 'postalCode', 'Customer'),
(37, 'phone', 'Customer'),
(38, 'fax', 'Customer'),
(39, 'category_name', 'Category'),
(40, 'categoryId', 'Product');

-- --------------------------------------------------------

--
-- Table structure for table `products`
--

DROP TABLE IF EXISTS `products`;
CREATE TABLE IF NOT EXISTS `products` (
`id` int(11) NOT NULL,
  `created_on` datetime DEFAULT NULL,
  `updated_on` datetime DEFAULT CURRENT_TIMESTAMP,
  `sku` varchar(100) DEFAULT NULL,
  `type` varchar(100) DEFAULT NULL,
  `has_options` int(11) DEFAULT NULL,
  `required_options` int(11) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `products`
--

INSERT INTO `products` (`id`, `created_on`, `updated_on`, `sku`, `type`, `has_options`, `required_options`) VALUES
(1, '2015-02-04 03:56:00', '2015-02-04 03:56:00', 'Life_is_What_You_Make_it', 'Product', 1, 1),
(2, '2015-02-04 04:06:23', '2015-02-04 04:06:23', 'Train_to_Pakistan', 'Product', 1, 1),
(3, '2015-02-04 04:15:19', '2015-02-04 04:15:19', 'Mango_Chutney', 'Product', 1, 1),
(4, '2015-02-04 04:21:26', '2015-02-04 04:21:26', 'Faulks_on_Fiction', 'Product', 1, 1),
(5, '2015-02-04 04:39:41', '2015-02-04 04:39:41', 'Telling_True_Stories', 'Product', 1, 1),
(6, '2015-02-04 04:54:01', '2015-02-04 04:54:01', 'Strontium_8GB_MicroSDHC_Memory_Card_6', 'Product', 1, 1),
(7, '2015-02-04 05:04:01', '2015-02-04 05:04:01', 'Micromax_Canvas_Power_A96_Black', 'Product', 1, 1),
(8, '2015-02-04 05:16:30', '2015-02-04 05:16:30', 'Micromax_Canvas_Power_A96_Black', 'Product', 1, 1);

-- --------------------------------------------------------

--
-- Table structure for table `products_master`
--

DROP TABLE IF EXISTS `products_master`;
CREATE TABLE IF NOT EXISTS `products_master` (
`id` int(11) NOT NULL,
  `Name` varchar(100) NOT NULL,
  `Description` varchar(255) NOT NULL,
  `Short_description` varchar(255) NOT NULL,
  `SKU` varchar(255) NOT NULL,
  `Weight` float(10,2) NOT NULL,
  `From_date` datetime NOT NULL,
  `To_date` datetime NOT NULL,
  `Status` varchar(50) NOT NULL,
  `URL_key` varchar(255) NOT NULL,
  `Visibility` int(11) NOT NULL,
  `Country` varchar(255) NOT NULL,
  `Price` float(10,2) NOT NULL,
  `Image` varchar(255) NOT NULL,
  `Qty` int(11) NOT NULL,
  `Stock` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `product_attribute_values`
--

DROP TABLE IF EXISTS `product_attribute_values`;
CREATE TABLE IF NOT EXISTS `product_attribute_values` (
`id` int(11) NOT NULL,
  `product_id` int(11) NOT NULL,
  `attribute_value_id` int(11) NOT NULL,
  `default_value` tinyint(1) NOT NULL,
  `position` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `product_entity_values`
--

DROP TABLE IF EXISTS `product_entity_values`;
CREATE TABLE IF NOT EXISTS `product_entity_values` (
`id` int(11) NOT NULL,
  `product_id` int(11) NOT NULL,
  `attribute_id` int(11) NOT NULL,
  `language` varchar(10) NOT NULL,
  `value` text NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=153 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `product_entity_values`
--

INSERT INTO `product_entity_values` (`id`, `product_id`, `attribute_id`, `language`, `value`) VALUES
(1, 1, 1, 'enUS', 'Life is What You Make it'),
(2, 1, 2, 'enUS', 'Ankita Sharma has the world in her palms. She is young, smart and heads turn at every corner she walks by. Born into a conservative middle class household - this defines the chronicle of her life. Set in a time when Doordarshan was the prime source of entertainment and writing love letters was the general fad, every youngster dreams of the thrills of college life. And so, her admission into an MBA institute in Mumbai follows. Ankita''s story begins here, from her life as a college student. Life seems all sunshine and flowers until a drastic turn leaves her staring at a disturbing path, only because of her own misdoing. Jump to six months later'),
(3, 1, 3, 'enUS', 'Ankita Sharma has the world in her palms. She is young, smart and heads turn at every corner she walks by. Born into a conservative middle class household - this defines the chronicle of her life. Set in a time when Doordarshan was the prime source of entertainment and writing love letters was the general fad, every youngster dreams of the thrills of college life. And so, her admission into an MBA institute in Mumbai follows. Ankita''s story begins here, from her life as a college student. Life seems all sunshine and flowers until a drastic turn leaves her staring at a disturbing path, only because of her own misdoing. Jump to six months later'),
(4, 1, 4, 'enUS', 'Life_is_What_You_Make_it'),
(5, 1, 5, 'enUS', '10.0'),
(6, 1, 6, 'enUS', '19/01/2011'),
(7, 1, 7, 'enUS', '19/01/2015'),
(8, 1, 8, 'enUS', '1'),
(9, 1, 9, 'enUS', 'life_is_what_you_make_it'),
(10, 1, 40, 'enUS', '2'),
(11, 1, 10, 'enUS', '1'),
(12, 1, 11, 'enUS', 'India'),
(13, 1, 12, 'enUS', '51.0'),
(14, 1, 13, 'enUS', 'p1.jpg'),
(15, 1, 14, 'enUS', '100'),
(16, 1, 15, 'enUS', '1'),
(17, 1, 17, 'enUS', '1'),
(18, 1, 16, 'enUS', 'Product'),
(19, 1, 18, 'enUS', '1'),
(20, 2, 1, 'enUS', 'Train to Pakistan'),
(21, 2, 2, 'enUS', 'Set in the summer of 1947, Khushwant Singh could never have been so straight from his heart than he was in this book, Train to Pakistan. A love story set in the village of Mano Majra, situated on the border between India and Pakistan, the novel has successfully portrayed the days of partition - the strife, the grievances, the loss, the struggles, the emotional turmoil. As the story progresses, it becomes very hard to accept the fact that both Muslims and Hindus were to blame. Starkly Khushwant Singh explains the blood shedding and sufferings of children and women, it brings a shiver to your body.'),
(22, 2, 3, 'enUS', 'Set in the summer of 1947, Khushwant Singh could never have been so straight from his heart than he was in this book, Train to Pakistan. A love story set in the village of Mano Majra, situated on the border between India and Pakistan, the novel has successfully portrayed the days of partition - the strife, the grievances, the loss, the struggles, the emotional turmoil. As the story progresses, it becomes very hard to accept the fact that both Muslims and Hindus were to blame. Starkly Khushwant Singh explains the blood shedding and sufferings of children and women, it brings a shiver to your body.'),
(23, 2, 4, 'enUS', 'Train_to_Pakistan'),
(24, 2, 5, 'enUS', '10.0'),
(25, 2, 6, 'enUS', '19/01/2011'),
(26, 2, 7, 'enUS', '19/01/2015'),
(27, 2, 8, 'enUS', '1'),
(28, 2, 9, 'enUS', 'train_to_pakistan'),
(29, 2, 40, 'enUS', '2'),
(30, 2, 10, 'enUS', '1'),
(31, 2, 11, 'enUS', 'India'),
(32, 2, 12, 'enUS', '147.0'),
(33, 2, 13, 'enUS', 'p2.jpg'),
(34, 2, 14, 'enUS', '100'),
(35, 2, 15, 'enUS', '1'),
(36, 2, 17, 'enUS', '1'),
(37, 2, 16, 'enUS', 'Product'),
(38, 2, 18, 'enUS', '1'),
(39, 3, 1, 'enUS', 'Mango Chutney: An Anthology of Tasteful Short Fiction'),
(40, 3, 2, 'enUS', 'In the sleepy rural town of Bikramganj, a halwai named Lachhuman recieves a letter from an unseen Lakshmi. A thousand kilometres away in Delhi, a woman waits an eternity for her birth certificate. In the sweltering heat of Chennai, a girl finds solace in her grandfather''s shirt. The stakes are high on the Euro rail on its way to Budapest where two Austrians try to trick an Indian woman at a card game. Back home in Vizag, an egg is being poarched to perfection. Mango Chutney, true to its name, is potpourri of quality short-fiction by some of India''s finest writers. Compiled and edited by bestselling author, Harsh Snehanshu, these stories, with just the right amount of sugar and tang, shall provoke, tickle and above all, linger your mind.'),
(41, 3, 3, 'enUS', 'In the sleepy rural town of Bikramganj, a halwai named Lachhuman recieves a letter from an unseen Lakshmi. A thousand kilometres away in Delhi, a woman waits an eternity for her birth certificate. In the sweltering heat of Chennai, a girl finds solace in her grandfather''s shirt. The stakes are high on the Euro rail on its way to Budapest where two Austrians try to trick an Indian woman at a card game. Back home in Vizag, an egg is being poarched to perfection. Mango Chutney, true to its name, is potpourri of quality short-fiction by some of India''s finest writers. Compiled and edited by bestselling author, Harsh Snehanshu, these stories, with just the right amount of sugar and tang, shall provoke, tickle and above all, linger your mind.'),
(42, 3, 4, 'enUS', 'Mango_Chutney'),
(43, 3, 5, 'enUS', '10.0'),
(44, 3, 6, 'enUS', '19/01/2011'),
(45, 3, 7, 'enUS', '19/01/2015'),
(46, 3, 8, 'enUS', '1'),
(47, 3, 9, 'enUS', 'mango_chutney'),
(48, 3, 40, 'enUS', '2'),
(49, 3, 10, 'enUS', '1'),
(50, 3, 11, 'enUS', 'India'),
(51, 3, 12, 'enUS', '99.0'),
(52, 3, 13, 'enUS', 'p3.jpg'),
(53, 3, 14, 'enUS', '100'),
(54, 3, 15, 'enUS', '1'),
(55, 3, 17, 'enUS', '1'),
(56, 3, 16, 'enUS', 'Product'),
(57, 3, 18, 'enUS', '1'),
(58, 4, 1, 'enUS', 'Faulks on Fiction'),
(59, 4, 2, 'enUS', 'Ever since Robinson Crusoe in 1719, the novel has introduced British readers to truly unforgettable characters - people in whom we can find deeper understanding of our own lives. In this engaging and personal book, Sebastian Faulks examines and celebrates the most famous and best-loved of these dazzling fictional creations and their wider impact on British culture as a whole. From Sherlock Holmes and Mr Darcy to Emma Woodhouse and James Bond - this is the story of the heroes, lovers, snobs and villains in all of us.'),
(60, 4, 3, 'enUS', 'Ever since Robinson Crusoe in 1719, the novel has introduced British readers to truly unforgettable characters - people in whom we can find deeper understanding of our own lives. In this engaging and personal book, Sebastian Faulks examines and celebrates the most famous and best-loved of these dazzling fictional creations and their wider impact on British culture as a whole. From Sherlock Holmes and Mr Darcy to Emma Woodhouse and James Bond - this is the story of the heroes, lovers, snobs and villains in all of us.'),
(61, 4, 4, 'enUS', 'Faulks_on_Fiction'),
(62, 4, 5, 'enUS', '10.0'),
(63, 4, 6, 'enUS', '19/01/2011'),
(64, 4, 7, 'enUS', '19/01/2015'),
(65, 4, 8, 'enUS', '1'),
(66, 4, 9, 'enUS', 'faulks_on_fiction'),
(67, 4, 40, 'enUS', '2'),
(68, 4, 10, 'enUS', '1'),
(69, 4, 11, 'enUS', 'India'),
(70, 4, 12, 'enUS', '297.0'),
(71, 4, 13, 'enUS', 'p4.jpg'),
(72, 4, 14, 'enUS', '100'),
(73, 4, 15, 'enUS', '1'),
(74, 4, 17, 'enUS', '1'),
(75, 4, 16, 'enUS', 'Product'),
(76, 4, 18, 'enUS', '1'),
(77, 5, 1, 'enUS', 'Telling True Stories'),
(78, 5, 2, 'enUS', 'Explores the key challenges in writing narrative non-fiction, and shows how some of the best in the business do it - an invaluable guide for anyone who wants to tell true stories well.\r\nMatthew Ricketson is Professor of Journalism at the University of Canberra and has worked as a journalist at The Age, The Australian and Time Australia magazine. He is the author of the bestselling guide Writing Feature Stories and of a biography of children''s author Paul Jennings, and editor of The Best Australian Profiles. '),
(79, 5, 3, 'enUS', 'Explores the key challenges in writing narrative non-fiction, and shows how some of the best in the business do it - an invaluable guide for anyone who wants to tell true stories well.\r\nMatthew Ricketson is Professor of Journalism at the University of Canberra and has worked as a journalist at The Age, The Australian and Time Australia magazine. He is the author of the bestselling guide Writing Feature Stories and of a biography of children''s author Paul Jennings, and editor of The Best Australian Profiles. '),
(80, 5, 4, 'enUS', 'Telling_True_Stories'),
(81, 5, 5, 'enUS', '10.0'),
(82, 5, 6, 'enUS', '19/01/2011'),
(83, 5, 7, 'enUS', '19/01/2015'),
(84, 5, 8, 'enUS', '1'),
(85, 5, 9, 'enUS', 'telling_true_stories'),
(86, 5, 40, 'enUS', '3'),
(87, 5, 10, 'enUS', '1'),
(88, 5, 11, 'enUS', 'India'),
(89, 5, 12, 'enUS', '1120.0'),
(90, 5, 13, 'enUS', 'p5.jpg'),
(91, 5, 14, 'enUS', '100'),
(92, 5, 15, 'enUS', '1'),
(93, 5, 17, 'enUS', '1'),
(94, 5, 16, 'enUS', 'Product'),
(95, 5, 18, 'enUS', '1'),
(96, 6, 1, 'enUS', 'Strontium 8GB MicroSDHC Memory Card (Class 6)'),
(97, 6, 2, 'enUS', 'If you need a thunder-speed data transfer, the more you should try out Strontium MicroSD card class 6 that would show you the real power of speed. With an improvement of transfer speed to 6Mb/s, you will be amazed that the data transfer is complete with a blink of your eyes. It can store higher definition of videos, higher resolution of photos and you don’t have to worry about losing your precious data.'),
(98, 6, 3, 'enUS', 'If you need a thunder-speed data transfer, the more you should try out Strontium MicroSD card class 6 that would show you the real power of speed. With an improvement of transfer speed to 6Mb/s, you will be amazed that the data transfer is complete with a blink of your eyes. It can store higher definition of videos, higher resolution of photos and you don’t have to worry about losing your precious data.'),
(99, 6, 4, 'enUS', 'Strontium_8GB_MicroSDHC_Memory_Card_6'),
(100, 6, 5, 'enUS', '10.0'),
(101, 6, 6, 'enUS', '19/01/2011'),
(102, 6, 7, 'enUS', '19/01/2015'),
(103, 6, 8, 'enUS', '1'),
(104, 6, 9, 'enUS', 'strontium_8gb_microSDHC_memory_card_6'),
(105, 6, 40, 'enUS', '4'),
(106, 6, 10, 'enUS', '1'),
(107, 6, 11, 'enUS', 'India'),
(108, 6, 12, 'enUS', '195.0'),
(109, 6, 13, 'enUS', 'p6.jpg'),
(110, 6, 14, 'enUS', '100'),
(111, 6, 15, 'enUS', '1'),
(112, 6, 17, 'enUS', '1'),
(113, 6, 16, 'enUS', 'Product'),
(114, 6, 18, 'enUS', '1'),
(115, 7, 1, 'enUS', 'Micromax Canvas Power A96 (Black)'),
(116, 7, 2, 'enUS', 'If you''re looking for an intelligent smartphone that gives you a processing experience like never before, you can go for the Micromax Canvas Power A96. At the heart of this phone lies the 1.3GHz MTK 6582M Quad Core processor that gives you lightning fast speed for your videos and gaming applications. Added to that is the 512MB RAM that allows you to run many high-end applications at once without any lag or slowdown. So, if you love videos and gaming, the Micromax A96 can be an ideal choice for you.'),
(117, 7, 3, 'enUS', 'If you''re looking for an intelligent smartphone that gives you a processing experience like never before, you can go for the Micromax Canvas Power A96. At the heart of this phone lies the 1.3GHz MTK 6582M Quad Core processor that gives you lightning fast speed for your videos and gaming applications. Added to that is the 512MB RAM that allows you to run many high-end applications at once without any lag or slowdown. So, if you love videos and gaming, the Micromax A96 can be an ideal choice for you.'),
(118, 7, 4, 'enUS', 'Micromax_Canvas_Power_A96_Black'),
(119, 7, 5, 'enUS', '10.0'),
(120, 7, 6, 'enUS', '19/01/2011'),
(121, 7, 7, 'enUS', '19/01/2015'),
(122, 7, 8, 'enUS', '1'),
(123, 7, 9, 'enUS', 'micromax_canvas_power_A96_black'),
(124, 7, 40, 'enUS', '5'),
(125, 7, 10, 'enUS', '1'),
(126, 7, 11, 'enUS', 'India'),
(127, 7, 12, 'enUS', '5849.0'),
(128, 7, 13, 'enUS', 'p7.jpg'),
(129, 7, 14, 'enUS', '100'),
(130, 7, 15, 'enUS', '1'),
(131, 7, 17, 'enUS', '1'),
(132, 7, 16, 'enUS', 'Product'),
(133, 7, 18, 'enUS', '1'),
(134, 8, 1, 'enUS', 'Lenovo A269i (Black)'),
(135, 8, 2, 'enUS', 'The Lenovo A269i features a 3.5 inch HVGA LCD display with a display resolution of 800x480 pixels and a good pixel resolution of about 250 ppi. The screen brings life to colours with the capability of displaying 16 million of them. Underneath the hood, it is powered by a 1GHz dual core processor by Mediatek, augmented by 256MB of RAM and comes with the Android 2.3 Gingerbread Operating system.'),
(136, 8, 3, 'enUS', 'The Lenovo A269i features a 3.5 inch HVGA LCD display with a display resolution of 800x480 pixels and a good pixel resolution of about 250 ppi. The screen brings life to colours with the capability of displaying 16 million of them. Underneath the hood, it is powered by a 1GHz dual core processor by Mediatek, augmented by 256MB of RAM and comes with the Android 2.3 Gingerbread Operating system.'),
(137, 8, 4, 'enUS', 'Lenovo_A269i_black'),
(138, 8, 5, 'enUS', '10.0'),
(139, 8, 6, 'enUS', '19/01/2011'),
(140, 8, 7, 'enUS', '19/01/2015'),
(141, 8, 8, 'enUS', '1'),
(142, 8, 9, 'enUS', 'lenovo_A269i_black'),
(143, 8, 40, 'enUS', '5'),
(144, 8, 10, 'enUS', '1'),
(145, 8, 11, 'enUS', 'India'),
(146, 8, 12, 'enUS', '2837.0'),
(147, 8, 13, 'enUS', 'p8.jpg'),
(148, 8, 14, 'enUS', '100'),
(149, 8, 15, 'enUS', '1'),
(150, 8, 17, 'enUS', '1'),
(151, 8, 16, 'enUS', 'Product'),
(152, 8, 18, 'enUS', '1');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `attribute`
--
ALTER TABLE `attribute`
 ADD PRIMARY KEY (`id`);

--
-- Indexes for table `attribute_group`
--
ALTER TABLE `attribute_group`
 ADD PRIMARY KEY (`id`);

--
-- Indexes for table `attribute_set`
--
ALTER TABLE `attribute_set`
 ADD PRIMARY KEY (`id`);

--
-- Indexes for table `attribute_values`
--
ALTER TABLE `attribute_values`
 ADD PRIMARY KEY (`id`);

--
-- Indexes for table `categories`
--
ALTER TABLE `categories`
 ADD PRIMARY KEY (`id`);

--
-- Indexes for table `category_entity_values`
--
ALTER TABLE `category_entity_values`
 ADD PRIMARY KEY (`id`);

--
-- Indexes for table `category_products`
--
ALTER TABLE `category_products`
 ADD PRIMARY KEY (`id`);

--
-- Indexes for table `checkout_order`
--
ALTER TABLE `checkout_order`
 ADD PRIMARY KEY (`id`);

--
-- Indexes for table `checkout_order_address`
--
ALTER TABLE `checkout_order_address`
 ADD PRIMARY KEY (`id`);

--
-- Indexes for table `checkout_order_item`
--
ALTER TABLE `checkout_order_item`
 ADD PRIMARY KEY (`id`);

--
-- Indexes for table `checkout_payment_methods`
--
ALTER TABLE `checkout_payment_methods`
 ADD PRIMARY KEY (`id`);

--
-- Indexes for table `checkout_quote`
--
ALTER TABLE `checkout_quote`
 ADD PRIMARY KEY (`id`);

--
-- Indexes for table `checkout_quote_address`
--
ALTER TABLE `checkout_quote_address`
 ADD PRIMARY KEY (`id`);

--
-- Indexes for table `checkout_quote_item`
--
ALTER TABLE `checkout_quote_item`
 ADD PRIMARY KEY (`id`);

--
-- Indexes for table `customer`
--
ALTER TABLE `customer`
 ADD PRIMARY KEY (`id`);

--
-- Indexes for table `customer_address`
--
ALTER TABLE `customer_address`
 ADD PRIMARY KEY (`id`);

--
-- Indexes for table `customer_address_attribute_values`
--
ALTER TABLE `customer_address_attribute_values`
 ADD PRIMARY KEY (`id`);

--
-- Indexes for table `customer_entity_values`
--
ALTER TABLE `customer_entity_values`
 ADD PRIMARY KEY (`id`);

--
-- Indexes for table `customer_group`
--
ALTER TABLE `customer_group`
 ADD PRIMARY KEY (`id`);

--
-- Indexes for table `customer_master`
--
ALTER TABLE `customer_master`
 ADD PRIMARY KEY (`id`);

--
-- Indexes for table `entities`
--
ALTER TABLE `entities`
 ADD PRIMARY KEY (`id`);

--
-- Indexes for table `products`
--
ALTER TABLE `products`
 ADD PRIMARY KEY (`id`);

--
-- Indexes for table `products_master`
--
ALTER TABLE `products_master`
 ADD PRIMARY KEY (`id`);

--
-- Indexes for table `product_attribute_values`
--
ALTER TABLE `product_attribute_values`
 ADD PRIMARY KEY (`id`);

--
-- Indexes for table `product_entity_values`
--
ALTER TABLE `product_entity_values`
 ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `attribute`
--
ALTER TABLE `attribute`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `attribute_group`
--
ALTER TABLE `attribute_group`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `attribute_set`
--
ALTER TABLE `attribute_set`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `attribute_values`
--
ALTER TABLE `attribute_values`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT for table `categories`
--
ALTER TABLE `categories`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=20;
--
-- AUTO_INCREMENT for table `category_entity_values`
--
ALTER TABLE `category_entity_values`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=15;
--
-- AUTO_INCREMENT for table `category_products`
--
ALTER TABLE `category_products`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=9;
--
-- AUTO_INCREMENT for table `checkout_order`
--
ALTER TABLE `checkout_order`
MODIFY `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT 'Id';
--
-- AUTO_INCREMENT for table `checkout_order_address`
--
ALTER TABLE `checkout_order_address`
MODIFY `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT 'Checkout Order Address Id';
--
-- AUTO_INCREMENT for table `checkout_order_item`
--
ALTER TABLE `checkout_order_item`
MODIFY `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT 'Order Item Id';
--
-- AUTO_INCREMENT for table `checkout_payment_methods`
--
ALTER TABLE `checkout_payment_methods`
MODIFY `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT 'Payment Method Id';
--
-- AUTO_INCREMENT for table `checkout_quote`
--
ALTER TABLE `checkout_quote`
MODIFY `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT 'Id';
--
-- AUTO_INCREMENT for table `checkout_quote_address`
--
ALTER TABLE `checkout_quote_address`
MODIFY `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT 'Checkout Quote Address Id';
--
-- AUTO_INCREMENT for table `checkout_quote_item`
--
ALTER TABLE `checkout_quote_item`
MODIFY `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT 'Item Id';
--
-- AUTO_INCREMENT for table `customer`
--
ALTER TABLE `customer`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `customer_address`
--
ALTER TABLE `customer_address`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `customer_address_attribute_values`
--
ALTER TABLE `customer_address_attribute_values`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `customer_entity_values`
--
ALTER TABLE `customer_entity_values`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `customer_group`
--
ALTER TABLE `customer_group`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT for table `customer_master`
--
ALTER TABLE `customer_master`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `entities`
--
ALTER TABLE `entities`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=41;
--
-- AUTO_INCREMENT for table `products`
--
ALTER TABLE `products`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=9;
--
-- AUTO_INCREMENT for table `products_master`
--
ALTER TABLE `products_master`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `product_attribute_values`
--
ALTER TABLE `product_attribute_values`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `product_entity_values`
--
ALTER TABLE `product_entity_values`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=153;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
