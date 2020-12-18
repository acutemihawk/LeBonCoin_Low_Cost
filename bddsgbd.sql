-- phpMyAdmin SQL Dump
-- version 4.7.0
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le :  ven. 18 déc. 2020 à 19:10
-- Version du serveur :  5.7.17
-- Version de PHP :  5.6.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `bddsgbd`
--

-- --------------------------------------------------------

--
-- Structure de la table `advertisment`
--

CREATE TABLE `advertisment` (
  `idAdvertisment` int(11) NOT NULL,
  `localisation` varchar(50) COLLATE utf8_bin NOT NULL,
  `price` int(11) NOT NULL,
  `description` varchar(500) COLLATE utf8_bin NOT NULL,
  `category` varchar(50) COLLATE utf8_bin NOT NULL,
  `titre` varchar(50) COLLATE utf8_bin NOT NULL,
  `iduser` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Déchargement des données de la table `advertisment`
--

INSERT INTO `advertisment` (`idAdvertisment`, `localisation`, `price`, `description`, `category`, `titre`, `iduser`) VALUES
(1, 'Paris', 1300, 'Iphone très bon état, recu recemment', 'Smartphone', 'Iphone pas cher !', 2),
(2, 'Tours', 450, 'Playstation que j\'ai volé dans le tram, vraiment pas cher !!', 'Consoles', 'PS5', 2),
(3, 'Marseille', 550, 'Tablette presque neuve !! Ouvert aux négociations', 'Tablette', 'Tablette Galaxy Tab 2 !', 1),
(4, 'Annecy', 80, 'Micro onde qui chauffe bien les plats !! Non ouverts aux négociations', 'Electromenager', 'Micro-onde Schmit', 3),
(5, 'Monaco', 10000, 'Rolls-Royce pas cher du tout, à la portée de tout le monde !!', 'Automobile', 'Rolls-Royce Phantom VIII ', 3);

-- --------------------------------------------------------

--
-- Structure de la table `offer`
--

CREATE TABLE `offer` (
  `idoffer` int(11) NOT NULL,
  `idAdvertisment` int(11) NOT NULL,
  `iduser` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- --------------------------------------------------------

--
-- Structure de la table `offerinfo`
--

CREATE TABLE `offerinfo` (
  `idoffer` int(11) NOT NULL,
  `price_offer` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- --------------------------------------------------------

--
-- Structure de la table `user`
--

CREATE TABLE `user` (
  `iduser` int(11) NOT NULL,
  `username` varchar(50) COLLATE utf8_bin NOT NULL,
  `password` varchar(50) COLLATE utf8_bin NOT NULL,
  `mail` varchar(50) COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Déchargement des données de la table `user`
--

INSERT INTO `user` (`iduser`, `username`, `password`, `mail`) VALUES
(1, 'Jean', '1234', 'jean@gmail.com'),
(2, 'Batiste', 'azerty', 'batiste@gmail.com'),
(3, 'Shen', 'toto', 'shen@gmail.com');

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `advertisment`
--
ALTER TABLE `advertisment`
  ADD PRIMARY KEY (`idAdvertisment`),
  ADD KEY `Advertisment_User_FK` (`iduser`);

--
-- Index pour la table `offer`
--
ALTER TABLE `offer`
  ADD PRIMARY KEY (`idoffer`,`idAdvertisment`,`iduser`),
  ADD KEY `Offer_Advertisment0_FK` (`idAdvertisment`),
  ADD KEY `Offer_User1_FK` (`iduser`);

--
-- Index pour la table `offerinfo`
--
ALTER TABLE `offerinfo`
  ADD PRIMARY KEY (`idoffer`);

--
-- Index pour la table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`iduser`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `advertisment`
--
ALTER TABLE `advertisment`
  MODIFY `idAdvertisment` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
--
-- AUTO_INCREMENT pour la table `offerinfo`
--
ALTER TABLE `offerinfo`
  MODIFY `idoffer` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;
--
-- AUTO_INCREMENT pour la table `user`
--
ALTER TABLE `user`
  MODIFY `iduser` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `advertisment`
--
ALTER TABLE `advertisment`
  ADD CONSTRAINT `Advertisment_User_FK` FOREIGN KEY (`iduser`) REFERENCES `user` (`iduser`);

--
-- Contraintes pour la table `offer`
--
ALTER TABLE `offer`
  ADD CONSTRAINT `Offer_Advertisment0_FK` FOREIGN KEY (`idAdvertisment`) REFERENCES `advertisment` (`idAdvertisment`),
  ADD CONSTRAINT `Offer_OfferInfo_FK` FOREIGN KEY (`idoffer`) REFERENCES `offerinfo` (`idoffer`),
  ADD CONSTRAINT `Offer_User1_FK` FOREIGN KEY (`iduser`) REFERENCES `user` (`iduser`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
