# Proves - Part 5

Totes les proves es fan des de `Main` → opció 3 (Usuaris), en una mateixa execució.

## Prova 1: dos usuaris amb la mateixa contrasenya

Registro `anna` i `pau`, tots dos amb la contrasenya `1234`.

```
Nom d'usuari: anna
Contrasenya: 1234
Usuari registrat:
  Nom:  anna
  Salt: 32-95-3A-01-16-3E-8A-8E-A8-6F-BE-80-4E-60-AB-CA
  Hash: B0-CB-1E-13-3F-76-AD-1E-DE-59-D3-FE-33-AB-4E-39-08-94-41-49-3A-EB-D0-11-FB-72-60-77-F0-E6-22-92

Nom d'usuari: pau
Contrasenya: 1234
Usuari registrat:
  Nom:  pau
  Salt: 9D-11-71-7F-9B-7B-17-EC-4F-1D-22-99-93-B7-32-56
  Hash: 55-93-3D-BA-76-EF-F2-F5-F9-E9-47-A8-45-63-2F-BF-3E-55-71-35-BF-BD-E0-4A-0F-38-06-99-AA-CF-11-CB
```

Tot i tenir la mateixa contrasenya, els hashes són diferents perquè cada usuari té una salt diferent.

## Prova 2: contrasenya correcta

```
Nom d'usuari: anna
Contrasenya: 1234
Hash calculat: B0-CB-1E-13-3F-76-AD-1E-DE-59-D3-FE-33-AB-4E-39-08-94-41-49-3A-EB-D0-11-FB-72-60-77-F0-E6-22-92
Hash guardat:  B0-CB-1E-13-3F-76-AD-1E-DE-59-D3-FE-33-AB-4E-39-08-94-41-49-3A-EB-D0-11-FB-72-60-77-F0-E6-22-92
Accés permès. Benvingut/da, anna
```

Els dos hashes coincideixen i es permet l'accés.

## Prova 3: contrasenya incorrecta

```
Nom d'usuari: anna
Contrasenya: 12345
Hash calculat: DC-DE-20-19-00-A6-2F-81-4D-A0-17-1A-0D-0C-77-47-51-A5-69-DD-F8-18-D3-60-0E-10-26-E3-56-EA-4B-B1
Hash guardat:  B0-CB-1E-13-3F-76-AD-1E-DE-59-D3-FE-33-AB-4E-39-08-94-41-49-3A-EB-D0-11-FB-72-60-77-F0-E6-22-92
Contrasenya incorrecta. Accés denegat
```

Amb una altra contrasenya el hash és diferent i no deixa entrar.

## Prova 4: salt diferent de la guardada

Opció 3 del menú d'usuaris, amb la contrasenya correcta (`1234`).

```
Nom d'usuari: anna
Contrasenya: 1234
Salt guardada:   32-95-3A-01-16-3E-8A-8E-A8-6F-BE-80-4E-60-AB-CA
Salt utilitzada: 50-63-53-19-A1-37-B3-A9-11-C4-EC-1B-C9-5E-54-20
Hash calculat: B4-D5-4C-D3-10-EF-B5-21-15-87-39-0A-C9-BA-CE-76-E2-2F-65-3F-80-FD-AE-CF-D1-41-0B-91-A8-5A-5F-BA
Hash guardat:  B0-CB-1E-13-3F-76-AD-1E-DE-59-D3-FE-33-AB-4E-39-08-94-41-49-3A-EB-D0-11-FB-72-60-77-F0-E6-22-92
Accés denegat: amb una salt diferent el hash no coincideix
```

Encara que la contrasenya és correcta, amb una altra salt el hash canvia i no es pot verificar l'usuari.

## Per què salts diferents i per què guardar-les

- **Diferents per a cada registre:** si no, dos usuaris amb la mateixa contrasenya tindrien el mateix hash, i descobrint-ne una es descobririen totes. També fa que no serveixin les taules de hashes precalculats (rainbow tables).
- **Cal guardar-la:** per comprovar la contrasenya a l'inici de sessió s'ha de tornar a calcular el hash amb la mateixa salt. Sense la salt original (prova 4) el hash no coincideix mai i l'usuari no podria entrar.
