Lista de Janot - Android App
-------

### O que é a Operação Lava Jato?

A operação Lava Jato é a maior investigação de corrupção e lavagem de dinheiro que o Brasil já teve. Estima-se que o volume de recursos desviados dos cofres da Petrobras, maior estatal do país, esteja na casa de bilhões de reais. Soma-se a isso a expressão econômica e política dos suspeitos de participar do esquema de corrupção que envolve a companhia.

### Por que fazer esse app?

Esse aplicativo é na verdade um trabalho para a disciplina de Laboratório de Desenvolvimento para Dispositivos Móveis da PUC Minas, mas eu resolvi caprichar um pouquinho e enviar para a Play Store.

### Build

Antes de compilar e rodar, crie um classe chamada TwitterConfig e configure-a da seguinte maneira:

```java

package me.cassiano.listadejanot;

import twitter4j.conf.ConfigurationBuilder;

public class TwitterConfig {

    // NUNCA commitar esse arquivo!

    public final static ConfigurationBuilder getConfigurationBuilder() {

        ConfigurationBuilder cb = new ConfigurationBuilder();

        cb.setDebugEnabled(true)
                .setOAuthConsumerKey("Sua consumer key")
                .setOAuthConsumerSecret("Sua consumer secret")
                .setOAuthAccessToken("Seu access token")
                .setOAuthAccessTokenSecret("Seu access token secret");

        return cb;

    }

}

```

Consiga suas chaves em: [Twitter Apps](https://apps.twitter.com)

### Links úteis

[AppCompat v21 — Material Design for Pre-Lollipop Devices!](http://android-developers.blogspot.com.br/2014/10/appcompat-v21-material-design-for-pre.html)

[Creating a Navigation Drawer](http://developer.android.com/training/implementing-navigation/nav-drawer.html)

[Using the Material theme](https://developer.android.com/training/material/theme.html)