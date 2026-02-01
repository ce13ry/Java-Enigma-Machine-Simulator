# Enigma Machine

## Encryption/Decryption machine

## Introduction
The *Enigma Machine* was a German encryption machine used in World War II. It works by including 3 out of a set of 5 possible rotars that each contain 26 different slots, with each slot mapping one key to another. An input key would travel through the rotars, hit a reflector at the end, and travel back through the rotars again, mapping a "trail" where letter 1 would map to letter 2. With each letter that passes through, the rotars shift in a certain way, ensuring that each letter is mapped uniquely depending on its position in the string.

## Purpose
This project will simulate the mechanics behind the *Enigma Machine* but with the option to include an arbitrary number of rotars, increasing the strength of the encryption.

## Who will use it?
Some possible use cases include:
- Sending important information via unsafe networks (bank account passwords)
- A company can use it to encrypt customer's personal information to keep it safe
- Keep backup data safe and secure
- Pass a note on how much you hate your English teacher to your buddy without getting caught

## Why is this project of interest to me
This project is of much interest to me because of the complexity and intricacy of this almost century-old analog machine. The elegancy of this purely physical solution to handling data inspired me to recreate it on a digital device. Loving history also helps :)

## User Stories
- **As a user, I want to be able to encrypt important information.**
- **As a user, I want my encryption to be strong.**
- **As a user, I want customizable unique encryptions for different use cases.** 
- **As a user, I want to be able to view the settings used in my encryption.**
- **As a user, I want to be able to save settings to file.**
- **As a user, I want to be able to load previous settings from file.**

# Instructions for End User
- Click on enigma machine to access settings
- Up and down arrows next to each rotar controls initial position for each rotar
- Click rotar to add rotar to enigma machine
- Click reset to clear all rotars
- Click save to save rotars
- Click load to load rotars
- Click back to return to main page
- Type on left page of the notebook to cipher text