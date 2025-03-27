# Enigma Machine

## Encryption/Decryption machine

## Introduction
The *Enigma Machine* was a German encryption machine used in world war II. It works by including 3 out of a set of 5 possible rotars that each contain 26 different slots, with each slot mapping one key to another. An input key would travel through the rotars, hit a reflector at the end, and travel back through the rotars again, mapping a "trail" where letter 1 would map to letter 2. With each letter that passes through, the rotars shift in a certain way, ensuring that each letter is mapped uniquely depending on its position in the string.

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
- **As a user, I want to be able to encrypt important information.** Robb Stark needs to tell Edmure Tully about his battle plans, but he is on McDonalds wifi and is too poor to afford a VPN. He chooses to not risk leaking his plans to the Lannisters and Edmure Tully ends up ruining his plan. Instead, Robb could've sent an encrypted message to Edmure informing him of his battle plans, the Lannisters intercept the message but cannot decrypt the message and Tywin Lannister falls into Robb's trap.
- **As a user, I want my encryption to be strong.** Julian has an admiring towards Janet, he writes a note to Brenden about his plan to prompose to her. Julian thinks he's clever and uses a simple caeser cipher to encrypt his messege, the teacher catches him passing the note and confiscates it. Julian is not worried as his note is encrypted, unfortunately, the teacher runs a simple brute force decryption on his note and reveals the message in O(n) time, embarrassing him in front of the class. Instead, Julian should have used an enigma machine to encrypt his message, when the teacher tries to use the same decryption method, her computer runs out of memory and crashes, giving Julian another opportunity to prompose.
- **As a user, I want customizable unique encryptions for different use cases.** Obi Wan Kenobi regularly sends encrypted messages to Anakin Skywalker. Unfortunately Palpatine initiates order 66 and the jedi are eliminated. Obi Wan decides it would be funny to taunt the emperor and sends the coordinates of the remaining jedi younglings in an encrypted form. Obi Wan is proud of his little joke as he knows the strength of the enigma encryption. However, he forgot that Anakin has turned to the dark side. Anakin intercepts the coordinates, decryptes them, and slaughters the remaining younglings :(. Instead, Kenobi could have changed his encryption by adding a different set and order of settings into his machine, that way when Anakin attempts to decipher the message with the previous setting, he will be unable to determine the location of the younglings.
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

# Phase 4: Task 2
Wed Mar 26 22:02:33 PDT 2025
Added rotar: 3 with initial position: 14
Wed Mar 26 22:02:36 PDT 2025
Added rotar: 2 with initial position: 71
Wed Mar 26 22:02:42 PDT 2025
Cleared all rotars