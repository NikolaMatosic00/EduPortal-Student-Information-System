-- MySQL Script generated by MySQL Workbench
-- Mon 28 Mar 2022 01:37:32 PM CEST
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema ssluzba
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema ssluzba
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `ssluzba` DEFAULT CHARACTER SET utf8 ;
USE `ssluzba` ;

-- -----------------------------------------------------
-- Table `ssluzba`.`smer`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ssluzba`.`smer` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `naziv` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ssluzba`.`student`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ssluzba`.`student` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `ime` VARCHAR(45) NULL,
  `prezime` VARCHAR(45) NULL,
  `broj_indexa` VARCHAR(45) NULL,
  `email` VARCHAR(45) NULL,
  `pass` VARCHAR(1000) NULL,
  `smer_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `broj_indexa_UNIQUE` (`broj_indexa` ASC) VISIBLE,
  UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE,
  INDEX `fk_student_smer1_idx` (`smer_id` ASC) VISIBLE,
  CONSTRAINT `fk_student_smer1`
    FOREIGN KEY (`smer_id`)
    REFERENCES `ssluzba`.`smer` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ssluzba`.`semestar`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ssluzba`.`semestar` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `broj_semestra` INT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ssluzba`.`predmet`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ssluzba`.`predmet` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `naziv` VARCHAR(45) NOT NULL,
  `silabus` VARCHAR(1000) NULL,
  `espb` INT NULL,
  `tip` VARCHAR(45) NULL,
  `smer_id` INT NOT NULL,
  `semestar_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_predmet_smer_idx` (`smer_id` ASC) VISIBLE,
  INDEX `fk_predmet_skolska_godina1_idx` (`semestar_id` ASC) VISIBLE,
  CONSTRAINT `fk_predmet_smer`
    FOREIGN KEY (`smer_id`)
    REFERENCES `ssluzba`.`smer` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_predmet_skolska_godina1`
    FOREIGN KEY (`semestar_id`)
    REFERENCES `ssluzba`.`semestar` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ssluzba`.`predavac`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ssluzba`.`predavac` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `ime` VARCHAR(45) NULL,
  `prezime` VARCHAR(45) NULL,
  `email` VARCHAR(45) NULL,
  `pass` VARCHAR(1000) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ssluzba`.`predavac_predmet`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ssluzba`.`predavac_predmet` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `uloga` VARCHAR(45) NOT NULL,
  `predmet_id` INT NOT NULL,
  `predavac_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_predavac_predmet_predmet1_idx` (`predmet_id` ASC) VISIBLE,
  INDEX `fk_predavac_predmet_predavac1_idx` (`predavac_id` ASC) VISIBLE,
  CONSTRAINT `fk_predavac_predmet_predmet1`
    FOREIGN KEY (`predmet_id`)
    REFERENCES `ssluzba`.`predmet` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_predavac_predmet_predavac1`
    FOREIGN KEY (`predavac_id`)
    REFERENCES `ssluzba`.`predavac` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ssluzba`.`student_pohadja`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ssluzba`.`student_pohadja` (
  `student_id` INT NOT NULL,
  `semestar_id` INT NOT NULL,
  `overen` TINYINT NULL,
  PRIMARY KEY (`student_id`, `semestar_id`),
  INDEX `fk_student_has_skolska_godina_skolska_godina1_idx` (`semestar_id` ASC) VISIBLE,
  INDEX `fk_student_has_skolska_godina_student1_idx` (`student_id` ASC) VISIBLE,
  CONSTRAINT `fk_student_has_skolska_godina_student1`
    FOREIGN KEY (`student_id`)
    REFERENCES `ssluzba`.`student` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_student_has_skolska_godina_skolska_godina1`
    FOREIGN KEY (`semestar_id`)
    REFERENCES `ssluzba`.`semestar` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ssluzba`.`slusa_predmet`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ssluzba`.`slusa_predmet` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `ocena` INT NULL,
  `predmet_id` INT NOT NULL,
  `student_id` INT NOT NULL,
  `elektronski_potpis` TINYINT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_slusa_predmet_predmet1_idx` (`predmet_id` ASC) VISIBLE,
  INDEX `fk_slusa_predmet_student1_idx` (`student_id` ASC) VISIBLE,
  CONSTRAINT `fk_slusa_predmet_predmet1`
    FOREIGN KEY (`predmet_id`)
    REFERENCES `ssluzba`.`predmet` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_slusa_predmet_student1`
    FOREIGN KEY (`student_id`)
    REFERENCES `ssluzba`.`student` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ssluzba`.`tip_polaganja`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ssluzba`.`tip_polaganja` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `naziv` VARCHAR(45) NOT NULL,
  `minimalno_za_prolaz` INT NULL,
  `ukupno` INT NULL,
  `minimalno_za_uslov` INT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ssluzba`.`pravila_polaganja`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ssluzba`.`pravila_polaganja` (
  `tip_polaganja_id` INT NOT NULL,
  `predmet_id` INT NOT NULL,
  PRIMARY KEY (`tip_polaganja_id`, `predmet_id`),
  INDEX `fk_tip_polaganja_has_predmet_predmet1_idx` (`predmet_id` ASC) VISIBLE,
  INDEX `fk_tip_polaganja_has_predmet_tip_polaganja1_idx` (`tip_polaganja_id` ASC) VISIBLE,
  CONSTRAINT `fk_tip_polaganja_has_predmet_tip_polaganja1`
    FOREIGN KEY (`tip_polaganja_id`)
    REFERENCES `ssluzba`.`tip_polaganja` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_tip_polaganja_has_predmet_predmet1`
    FOREIGN KEY (`predmet_id`)
    REFERENCES `ssluzba`.`predmet` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ssluzba`.`termin_polaganja`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ssluzba`.`termin_polaganja` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `naziv_roka` VARCHAR(45) NULL,
  `napomena` VARCHAR(45) NULL,
  `tip_polaganja_has_predmet_tip_polaganja_id` INT NOT NULL,
  `tip_polaganja_has_predmet_predmet_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_termin_polaganja_tip_polaganja_has_predmet1_idx` (`tip_polaganja_has_predmet_tip_polaganja_id` ASC, `tip_polaganja_has_predmet_predmet_id` ASC) VISIBLE,
  CONSTRAINT `fk_termin_polaganja_tip_polaganja_has_predmet1`
    FOREIGN KEY (`tip_polaganja_has_predmet_tip_polaganja_id` , `tip_polaganja_has_predmet_predmet_id`)
    REFERENCES `ssluzba`.`pravila_polaganja` (`tip_polaganja_id` , `predmet_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ssluzba`.`polaganje`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ssluzba`.`polaganje` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `ostvaren_broj_bodova` INT NULL,
  `vreme_prijave` DATETIME NULL,
  `slusa_predmet_id` INT NOT NULL,
  `termin_polaganja_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_polaganje_slusa_predmet1_idx` (`slusa_predmet_id` ASC) VISIBLE,
  INDEX `fk_polaganje_termin_polaganja1_idx` (`termin_polaganja_id` ASC) VISIBLE,
  CONSTRAINT `fk_polaganje_slusa_predmet1`
    FOREIGN KEY (`slusa_predmet_id`)
    REFERENCES `ssluzba`.`slusa_predmet` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_polaganje_termin_polaganja1`
    FOREIGN KEY (`termin_polaganja_id`)
    REFERENCES `ssluzba`.`termin_polaganja` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ssluzba`.`obavestenje`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ssluzba`.`obavestenje` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `tekst` VARCHAR(1000) NULL,
  `vreme_objave` DATETIME NULL,
  `predmet_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_obavestenje_predmet1_idx` (`predmet_id` ASC) VISIBLE,
  CONSTRAINT `fk_obavestenje_predmet1`
    FOREIGN KEY (`predmet_id`)
    REFERENCES `ssluzba`.`predmet` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ssluzba`.`admin`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ssluzba`.`admin` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `email` VARCHAR(45) NULL,
  `pass` VARCHAR(1000) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ssluzba`.`uplata`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ssluzba`.`uplata` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `iznos` DOUBLE NULL,
  `vreme_uplate` DATETIME NULL,
  `svrha` VARCHAR(45) NULL,
  `student_id` INT NOT NULL,
  `admin_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_uplata_student1_idx` (`student_id` ASC) VISIBLE,
  INDEX `fk_uplata_admin1_idx` (`admin_id` ASC) VISIBLE,
  CONSTRAINT `fk_uplata_student1`
    FOREIGN KEY (`student_id`)
    REFERENCES `ssluzba`.`student` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_uplata_admin1`
    FOREIGN KEY (`admin_id`)
    REFERENCES `ssluzba`.`admin` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
