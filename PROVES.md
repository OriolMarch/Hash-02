# Proves - Part 5

Totes les proves es fan des de `Main` → opció 3 (Usuaris), en una mateixa execució.

## Prova 1: dos usuaris amb la mateixa contrasenya

Registro `anna` i `pau`, tots dos amb la contrasenya `1234`.

```
Nom d'usuari: anna
Contrasenya: 1234
Usuari registrat:
  Nom:  anna
  Salt: 33-CD-21-5E-DF-6F-11-26-66-AF-2D-0F-A0-F8-18-99
  Hash: F1-79-FB-19-08-18-3A-84-3A-EA-75-90-99-73-B3-FC-4C-02-96-5D-B4-BC-FE-AE-0E-8A-CB-FA-F0-03-2C-E3

Nom d'usuari: pau
Contrasenya: 1234
Usuari registrat:
  Nom:  pau
  Salt: A8-F8-41-FF-87-CA-8E-1F-E3-2D-C2-32-8B-1E-32-6E
  Hash: C8-37-05-31-A5-61-D4-D8-1D-FD-3D-F9-DD-38-9E-63-15-BE-68-74-8F-A1-13-8A-8F-2B-57-B5-09-A8-B3-18
```

Tot i tenir la mateixa contrasenya, els hashes són diferents perquè cada usuari té una salt diferent.

## Prova 2: contrasenya correcta

```
Nom d'usuari: anna
Contrasenya: 1234
Hash calculat: F1-79-FB-19-08-18-3A-84-3A-EA-75-90-99-73-B3-FC-4C-02-96-5D-B4-BC-FE-AE-0E-8A-CB-FA-F0-03-2C-E3
Hash guardat:  F1-79-FB-19-08-18-3A-84-3A-EA-75-90-99-73-B3-FC-4C-02-96-5D-B4-BC-FE-AE-0E-8A-CB-FA-F0-03-2C-E3
Accés permès. Benvingut/da, anna
```

Els dos hashes coincideixen i es permet l'accés.

## Prova 3: contrasenya incorrecta

```
Nom d'usuari: anna
Contrasenya: 12345
Hash calculat: 5C-F4-5F-75-3A-9A-B0-F7-09-87-53-65-95-35-18-EF-2A-E6-35-D0-CF-F8-71-C5-72-9F-3A-BB-5A-6B-B8-B1
Hash guardat:  F1-79-FB-19-08-18-3A-84-3A-EA-75-90-99-73-B3-FC-4C-02-96-5D-B4-BC-FE-AE-0E-8A-CB-FA-F0-03-2C-E3
Contrasenya incorrecta. Accés denegat
```

Amb una altra contrasenya el hash és diferent i no deixa entrar.

## Prova 4: salt diferent de la guardada

Opció 3 del menú d'usuaris, amb la contrasenya correcta (`1234`).

```
Nom d'usuari: anna
Contrasenya: 1234
Salt guardada:   33-CD-21-5E-DF-6F-11-26-66-AF-2D-0F-A0-F8-18-99
Salt utilitzada: 3D-6C-9C-B9-4B-E2-C1-56-35-1D-BF-CF-7D-F5-5F-A1
Hash calculat: 3F-B4-58-A2-75-25-A1-FD-E2-F0-74-88-78-44-00-B3-FC-48-50-7F-86-6C-15-64-E0-B6-89-83-37-C9-96-DA
Hash guardat:  F1-79-FB-19-08-18-3A-84-3A-EA-75-90-99-73-B3-FC-4C-02-96-5D-B4-BC-FE-AE-0E-8A-CB-FA-F0-03-2C-E3
Accés denegat: amb una salt diferent el hash no coincideix
```

Encara que la contrasenya és correcta, amb una altra salt el hash canvia i no es pot verificar l'usuari.

## Per què salts diferents i per què guardar-les

- **Diferents per a cada registre:** si no, dos usuaris amb la mateixa contrasenya tindrien el mateix hash, i si un atacant descobreix la contrasenya d'un hash, coneix la de tots els usuaris que tenen aquell hash. També fa que no serveixin les taules de hashes precalculats (rainbow tables).
- **Cal guardar-la:** per comprovar la contrasenya a l'inici de sessió s'ha de tornar a calcular el hash amb la mateixa salt. Sense la salt original (prova 4) el hash no coincideix mai i l'usuari no podria entrar.
