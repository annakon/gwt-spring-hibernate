SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Table structure for table `employee`
--

CREATE TABLE IF NOT EXISTS `employee` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `birthday` datetime NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `experience` varchar(255) DEFAULT NULL,
  `firstname` varchar(255) NOT NULL,
  `lastname` varchar(255) NOT NULL,
  `secondname` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  FULLTEXT KEY `idx_employee_ft` (`description`,`experience`,`firstname`,`lastname`,`secondname`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 AUTO_INCREMENT=4 ;

--
-- Dumping data for table `employee`
--

INSERT INTO `employee` (`id`, `birthday`, `description`, `experience`, `firstname`, `lastname`, `secondname`) VALUES
(1, '1980-03-26 00:00:00', 'тесторый пользователь', 'java gwt hibernate', 'Вася', 'Пупкин', 'Васильевич'),
(2, '1983-01-14 00:00:00', 'Крутой разработчик', 'java spring big-data', 'Александр', 'Александров', 'Александрович'),
(3, '1990-12-06 00:00:00', 'младший разбработчик', 'java web', 'Игорь', 'Красюков', 'Семёнович');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
