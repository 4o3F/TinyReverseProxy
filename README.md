# TinyReverseProxy
A reverse proxy for Minecraft Server Intergration

## Cnfiguration

**Some of the words below is from [Plan](https://github.com/plan-player-analytics/Plan/)**

**Thank you for your fantastic work!**

### Step 1-3: Obtain Free Certificate from Let's Encrypt

Let's Encrypt is a free certificate authority that allows you to get a certificate for you domain easily.

You will be able to obtain `.pem` certificate files from them using Certbot.

Please have a read through: https://letsencrypt.org/getting-started/

##### If you have terminal access on your server

> 📢 **Important**
>
> Please read through the 5 steps below before continuing.

1. Open [Certbot tutorial](https://certbot.eff.org/)
2. Select 'None of the Above' and Operating System of **your server** (or OS of your PC if your server has no terminal access). 
3. Scroll down to 'default' / 'wildcard *' and select one. Wildcard cert is for all subdomains eg. `*.domain.com`.
4. Follow the steps in the *Certbot tutorial* until the step "Install your certificate" and continue *this tutorial*.
5. After completing *this tutorial*, go back to finish rest of the steps in *Certbot tutorial*.

##### If you don't have terminal access

Do the same steps as above, but instead of doing them on your server, do them on **your computer**.

> 💭 **I can't open a port on my router for certbot**
>
> Consider an alternative certificate provider or use a self-signed certificate.

You will need to have OpenSSL installed.

- Most linux distros ship with openssl installed.
- [OpenSSL Windows installation tutorial](https://www.osradar.com/install-openssl-windows/)

### Step 4: Creating PKCS12 from Certificate files

##### From .pem into PKCS12 format (.p12):

Create the .p12 file first

```
> touch /etc/letsencrypt/live/<domain>/pkcs.p12 
```

> 🚧 **Windows users**:
>
> ```
> > fsutil file createnew C:\Certbot\live\<domain>\pkcs.p12 0
> ```
>
> If you're using windows change all `/etc/letsencrypt/live/<domain>/` to `C:\Certbot\live\<domain>\` in the commands on this page

> 🚧 **FTP Client users**:
> Creating a new file on a FTP client may create a new text file, which will cause the pkcs12 command to fail!

```
> openssl pkcs12 -export \
-in /etc/letsencrypt/live/<domain>/fullchain.pem \
-inkey /etc/letsencrypt/live/<domain>/privkey.pem \	
-out /etc/letsencrypt/live/<domain>/pkcs.p12 \	
-passout pass:<password> 
```

### Step 4.1: Set Configuration Settings:

- Place the `.p12` file in the /plugins/TinyReversePorxy/ folder or specify the full path to the file.

| Config        | Description                                                  |
| ------------- | ------------------------------------------------------------ |
| localport     | The port you want to forward, such as 8123 for dynmap        |
| publicport    | The port you want to show to public, **shouldn't be the same as localport** |
| cert.name     | The file name of your .p12 file                              |
| cert.password | The password part of  `passout pass:<password>` entered above |

💭 **The page doesn't work after I set the settings!**

Make sure that you have `https://` in front of the address