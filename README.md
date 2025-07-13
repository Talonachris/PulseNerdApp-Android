<p align="center">
  <img src="assets/pulseflash_logo.png" alt="PulseNerd Logo" width="180" />
</p>

## ✅ PulseNerdApp-Android

**Project Goal:** Android companion app for WhatPulse (local Client API), focusing on **real-time stats in landscape mode** for power users, such as gamers and streamers.

---

### 💡 Core Idea

PulseNerd is the **sister app** of PulseView and PulseNerd for iOS. This Android version brings the same **live session tracking** experience – including Clicks per Second (CPS) and network throughput – to Android devices. It uses the **local WhatPulse Client API** (Port 3490, JSON), runs within the local network, and is **technically separate** from the cloud-based WhatPulse API.

---

### 📱 Platform Support

| Platform   | Status                                                   |
| ---------- | -------------------------------------------------------- |
| Android    | ✅ In development                                        |
| iPhone     | ✅ Fully supported (see `PulseNerdApp-iOS`)              |
| iPad       | ✅ Fully supported (iOS version)                         |
| Apple TV   | 🔢 Planned (later as visualization screen)              |
| Mac, Watch | ❌ Not planned                                            |
| Vision Pro | ❌ Not planned                                            |

---

### 🧠 Feature Overview

#### 🌟 Core Features

* Live line charts for:
  * 🖱️ Clicks per second
  * 🔽 Download speed (Mbps)
  * 🔼 Upload speed (Mbps)

* Session-based tracking:
  * Duration, peaks, averages, totals
  * Live values every 1–3 seconds
  * Data persists even after Pulse events

* Shareable session summary

#### 📊 Planned Views

* 🧠 **Geek View** – Animated overview with all current values
* 📶 **Network View** – Real-time download/upload line charts
* 🖱 **CPS View** – Clicks per second, peak/avg, session summary
* ⚡️ **PulseView** – Big pulse button + Pelle bark sound!
* ⚙️ **Settings View** – Local IP config, test connection, etc.
* 📱 **Our Apps** – Discover other Nebuliton apps

---

### 🛠️ Technical Foundations

* Kotlin & Jetpack Compose
* API: `http://<ip>:3490/` (JSON WhatPulse Client API)
* Landscape layout preferred
* Sound playback using Android MediaPlayer
* Adaptive design (phones first, tablets planned)
* Target: Android 12+ (API 31+)
* Manual GitHub deployment (no CI/CD yet)

---

### 🎨 Design & Branding

* App name: **PulseNerd**
* Icon: ⚡️ Red lightning bolt matching iOS version
* Offline-first: No internet needed, no user account
* Native Android design with custom dark UI
* Same layout principles as iOS app (familiar for users)

---

### 🚀 Release Roadmap

| Phase           | Description                                              |
| --------------- | -------------------------------------------------------- |
| ✅ Planning      | Project structure, repo setup                            |
| 🚧 Development   | Core views in progress (Live, CPS, Network)             |
| 🧪 Alpha Testing | Internal debug builds                                   |
| 🚀 Beta         | Public APKs / Google Play testing                        |
| 🎉 Release      | Google Play Store launch                                 |

---

© 2025 Nebuliton – Android version starts now! 🚀📱⚡️
