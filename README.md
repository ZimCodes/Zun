# Zun
A system file cleaner **currently supporting Windows only** made using Kotlin/Java

## Features

Zun has several cleaning options:

| Clean Type | Description | Locations |
|------------|--------------|-------------|
|Browser|Cleans the currently supported browsers *(Firefox, Google Chrome, Vivavldi, Edge Chromium, Brave)*.| Browser Folder Directories|
|Light System|Does a light cleaning of the system | %AppData%, %CommonProgramFiles(x86)%, %Windir%, %Temp%,%SystemDrive% 
|Full System|Full System cleaning including *temp files* & *browsers* |%AppData%,LowAppData,%LocalAppdata%,%ProgramData%,%CommonProgramFiles(x86)%, %Windir%, %Temp%, %SystemDrive%|
|Pictures|Removes all content from *Picture* folder| C:\\%UserProfile%\\Pictures|
|Downloads|Removes all content from *Downloads* folder| C:\\%UserProfile%\\Downloads|
|Music|Removes all content from *Music* folder| C:\\%UserProfile%\\Music|
|Videos|Removes all content from *Videos* folder| C:\\%UserProfile%\\Videos|
|User Cleanup|Removes all content from the *Picture*, *Downloads*, *Music*, & *Videos* folders|C:\\%UserProfile%\\Pictures, C:\\%UserProfile%\\Downloads, C:\\%UserProfile%\\Music, C:\\%UserProfile%\\Videos 

***Can only remove files not needing adminstration access** 

## License
Zun is licensed under the **GNU GENERAL PUBLIC LICENSE Version 2**.

For more information [click here](https://github.com/ZimCodes/Zun/blob/main/LICENSE).