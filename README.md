# android-tp-lefort-2026

Les codes de TP de développement mobile sous Android et Java #IAO.

## Installation et lancement

1. Placez le projet à simuler dans le dossier `IAO_App`.
2. Ouvrez Android Studio et importez le projet.
3. Compilez et lancez l'application.

## Nettoyage avant push

Pour effacer tous les dossiers de build, `.idea` et `.gradle` avant de faire un push, utilisez les commandes suivantes dans l'invite de commandes Windows :

```batch
for /d /r "C:\Users\AC2I\AndroidStudioProjects\android-tp-lefort-2026" %i in (build) do if exist "%i" rmdir /s /q "%i"
for /d /r "C:\Users\AC2I\AndroidStudioProjects\android-tp-lefort-2026" %i in (.idea) do if exist "%i" rmdir /s /q "%i"
for /d /r "C:\Users\AC2I\AndroidStudioProjects\android-tp-lefort-2026" %i in (.gradle) do if exist "%i" rmdir /s /q "%i"
```

## Notes

* Assurez-vous d'avoir la version correcte de Java et Android Studio.
* Ce projet est prévu pour l'apprentissage et les TP de l'I.A.O.
