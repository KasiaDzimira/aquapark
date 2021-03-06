; Script generated by the Inno Setup Script Wizard.
; SEE THE DOCUMENTATION FOR DETAILS ON CREATING INNO SETUP SCRIPT FILES!

[Setup]
; NOTE: The value of AppId uniquely identifies this application.
; Do not use the same AppId value in installers for other applications.
; (To generate a new GUID, click Tools | Generate GUID inside the IDE.)
AppId={{55157ED3-C534-444B-9EC6-9E1B0C82CF7B}
AppName=Aquapark
AppVersion=1.0
;AppVerName=Aquapark 1.0
AppPublisher=Aquapark application
DefaultDirName={pf}\Aquapark
DisableProgramGroupPage=yes
OutputDir=C:\Users\Kasia\Desktop\aquapark\setup
OutputBaseFilename=setup
SetupIconFile=C:\Users\Kasia\Desktop\aquapark\icons\icon.ico
Compression=lzma
SolidCompression=yes

[Languages]
Name: "english"; MessagesFile: "compiler:Default.isl"

[Tasks]
Name: "desktopicon"; Description: "{cm:CreateDesktopIcon}"; GroupDescription: "{cm:AdditionalIcons}"; Flags: unchecked

[Files]
Source: "C:\Users\Kasia\Desktop\aquapark\aquapark.exe"; DestDir: "{app}"; Flags: ignoreversion
; NOTE: Don't use "Flags: ignoreversion" on any shared system files

[Icons]
Name: "{commonprograms}\Aquapark"; Filename: "{app}\aquapark.exe"
Name: "{commondesktop}\Aquapark"; Filename: "{app}\aquapark.exe"; Tasks: desktopicon

[Run]
Filename: "{app}\aquapark.exe"; Description: "{cm:LaunchProgram,Aquapark}"; Flags: nowait postinstall skipifsilent

