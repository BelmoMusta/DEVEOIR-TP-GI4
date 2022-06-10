-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : mar. 07 juin 2022 à 12:30
-- Version du serveur : 10.4.22-MariaDB
-- Version de PHP : 7.3.33

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `test`
--

-- --------------------------------------------------------

--
-- Structure de la table `allocation`
--

CREATE TABLE `allocation` (
  `allocationid` int(255) NOT NULL,
  `userid` varchar(30) NOT NULL,
  `allocationdate` date NOT NULL,
  `productid` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `allocation`
--

INSERT INTO `allocation` (`allocationid`, `userid`, `allocationdate`, `productid`) VALUES
(2, '2', '2022-06-02', 'i2'),
(3, 'oussama', '2022-06-03', 'c1'),
(5, 'abc', '2022-06-04', 'c2'),
(7, 'abc', '2022-06-04', 'c22'),
(9, 'abc', '2022-06-04', 'c33'),
(10, 'abc', '2022-06-06', 'l47');

-- --------------------------------------------------------

--
-- Structure de la table `chaise`
--

CREATE TABLE `chaise` (
  `id` int(11) NOT NULL,
  `name` varchar(250) NOT NULL,
  `prix` varchar(250) NOT NULL,
  `hauteur` float NOT NULL,
  `code` varchar(250) NOT NULL,
  `isordered` varchar(30) NOT NULL DEFAULT 'false'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `chaise`
--

INSERT INTO `chaise` (`id`, `name`, `prix`, `hauteur`, `code`, `isordered`) VALUES
(1, 'kdksd', '33.0', 33, 'c1', 'false'),
(2, 'chaise43', '32.0', 44, 'c2', 'false'),
(3, 'chaise44', '33.0', 44, 'c22', 'false');

-- --------------------------------------------------------

--
-- Structure de la table `imprimante`
--

CREATE TABLE `imprimante` (
  `id` int(11) NOT NULL,
  `name` varchar(250) NOT NULL,
  `prix` varchar(250) NOT NULL,
  `type` varchar(250) NOT NULL,
  `code` varchar(250) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `imprimante`
--

INSERT INTO `imprimante` (`id`, `name`, `prix`, `type`, `code`) VALUES
(1, 'imprimante11', '223', 'laser', 'i2');

-- --------------------------------------------------------

--
-- Structure de la table `livre`
--

CREATE TABLE `livre` (
  `id` int(11) NOT NULL,
  `name` varchar(250) NOT NULL,
  `prix` varchar(250) NOT NULL,
  `auteur` varchar(250) NOT NULL,
  `code` varchar(250) NOT NULL,
  `isordered` varchar(20) NOT NULL DEFAULT 'false'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `livre`
--

INSERT INTO `livre` (`id`, `name`, `prix`, `auteur`, `code`, `isordered`) VALUES
(1, 'Livre1', '123', 'mohamed', 'L1', ''),
(2, 'Livre1', '123', 'mohamed', 'L1', ''),
(3, 'Livre1', '123', 'L1', 'mohamed', ''),
(4, 'livre4', '33', 'osama', 'ccdd', ''),
(5, 'livre222', '332.0', 'oussama', 'l47', 'true'),
(6, 'livree', '33.0', 'bing', 'l88', 'false');

-- --------------------------------------------------------


--
-- Structure de la table `user`
--

CREATE TABLE `user` (
  `userid` int(255) NOT NULL,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `isadmin` varchar(255) NOT NULL,
  `Name` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `user`
--

INSERT INTO `user` (`userid`, `username`, `password`, `isadmin`, `Name`) VALUES
(1, 'oussama', '123456', 'true', 'oussamamb'),
(2, 'abc', 'abc', 'false', 'bing');

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `allocation`
--
ALTER TABLE `allocation`
  ADD PRIMARY KEY (`allocationid`),
  ADD UNIQUE KEY `productid` (`productid`),
  ADD KEY `userid` (`userid`) USING BTREE;

--
-- Index pour la table `chaise`
--
ALTER TABLE `chaise`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `imprimante`
--
ALTER TABLE `imprimante`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `livre`
--
ALTER TABLE `livre`
  ADD PRIMARY KEY (`id`);


-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `allocation`
--
ALTER TABLE `allocation`
  MODIFY `allocationid` int(255) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT pour la table `chaise`
--
ALTER TABLE `chaise`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT pour la table `imprimante`
--
ALTER TABLE `imprimante`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT pour la table `livre`
--
ALTER TABLE `livre`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT pour la table `student`
--

COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
