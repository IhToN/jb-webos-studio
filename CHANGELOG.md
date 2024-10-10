# Changelog

All notable changes to this project will be documented in this file.
The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/), this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html) and to [Conventional Commits](https://www.conventionalcommits.org/en/v1.0.0/).

## [Unreleased]

## [[0.1.5]] - 2024-10-10

### Bug Fixes

- migrate kover tasks from 0.7 to 0.8 [`7072c8e`](https://github.com/IhToN/jb-webos-studio/commit/7072c8e)
- override getActionUpdateThread to choose BGT [`2f2ec82`](https://github.com/IhToN/jb-webos-studio/commit/2f2ec82)

### Build

- Bump org.jetbrains:annotations from 24.1.0 to 26.0.0 [`5afb423`](https://github.com/IhToN/jb-webos-studio/commit/5afb423)
- Bump org.jetbrains:marketplace-zip-signer [`09e04df`](https://github.com/IhToN/jb-webos-studio/commit/09e04df)
- Bump org.jetbrains.kotlinx.kover from 0.7.6 to 0.8.3 [`8d8a20d`](https://github.com/IhToN/jb-webos-studio/commit/8d8a20d)

### Chore

- regenerate changelog before releasing the draft [`005a36a`](https://github.com/IhToN/jb-webos-studio/commit/005a36a)
- set new headerParserRegex to work with conventional_changelog generator [`6cb38bc`](https://github.com/IhToN/jb-webos-studio/commit/6cb38bc)
- generate changelog before creating the draft [`dd6de03`](https://github.com/IhToN/jb-webos-studio/commit/dd6de03)
- change headerParserRegex for gradle-changelog-plugin [`e336272`](https://github.com/IhToN/jb-webos-studio/commit/e336272)
- move changelog generation from build to release action [`2043337`](https://github.com/IhToN/jb-webos-studio/commit/2043337)
- add missing env.GITHUB_TOKEN to github tasks [`e3071ec`](https://github.com/IhToN/jb-webos-studio/commit/e3071ec)
- add missing config user configuration [`07576fd`](https://github.com/IhToN/jb-webos-studio/commit/07576fd)
- add action to generate the changelog based on conventional commits [`43a39d8`](https://github.com/IhToN/jb-webos-studio/commit/43a39d8)
- update github actions [`b591e0a`](https://github.com/IhToN/jb-webos-studio/commit/b591e0a)
- increase version to 0.1.5 [`e060cbd`](https://github.com/IhToN/jb-webos-studio/commit/e060cbd)

### Other

- Merge remote-tracking branch 'refs/remotes/origin/dependabot/gradle/org.jetbrains.kotlinx.kover-0.8.3' [`ea49e50`](https://github.com/IhToN/jb-webos-studio/commit/ea49e50)
- Merge remote-tracking branch 'refs/remotes/origin/dependabot/gradle/org.jetbrains-marketplace-zip-signer-0.1.29' [`0904eb0`](https://github.com/IhToN/jb-webos-studio/commit/0904eb0)
- Merge remote-tracking branch 'refs/remotes/origin/dependabot/gradle/org.jetbrains-annotations-26.0.0' [`c21f023`](https://github.com/IhToN/jb-webos-studio/commit/c21f023)

## v[0.1.4] (2024-09-20)

### Build

- Bump org.jetbrains.kotlinx:kotlinx-serialization-json [`7b2de09`](https://github.com/IhToN/jb-webos-studio/commit/7b2de09)
- Bump org.jetbrains.qodana from 2024.1.9 to 2024.2.3 [`04185fa`](https://github.com/IhToN/jb-webos-studio/commit/04185fa)
- Bump JetBrains/qodana-action from 2024.1.9 to 2024.2.3 [`cc116c4`](https://github.com/IhToN/jb-webos-studio/commit/cc116c4)
- Bump org.jetbrains.kotlin.jvm from 2.0.10 to 2.0.20 [`af8a161`](https://github.com/IhToN/jb-webos-studio/commit/af8a161)

### Chore

- bump version to 0.1.4 [`7be6078`](https://github.com/IhToN/jb-webos-studio/commit/7be6078)
- add compatibility for 2024.3 IDE version [`3cb682d`](https://github.com/IhToN/jb-webos-studio/commit/3cb682d)

### Other

- Merge pull request #40 from IhToN/dependabot/gradle/org.jetbrains.kotlinx-kotlinx-serialization-json-1.7.3 [`a047766`](https://github.com/IhToN/jb-webos-studio/commit/a047766)
- Merge pull request #39 from IhToN/dependabot/gradle/org.jetbrains.qodana-2024.2.3 [`02f432e`](https://github.com/IhToN/jb-webos-studio/commit/02f432e)
- Merge pull request #34 from IhToN/dependabot/gradle/org.jetbrains.kotlin.jvm-2.0.20 [`c7b25f8`](https://github.com/IhToN/jb-webos-studio/commit/c7b25f8)
- Merge pull request #38 from IhToN/dependabot/github_actions/JetBrains/qodana-action-2024.2.3 [`360e862`](https://github.com/IhToN/jb-webos-studio/commit/360e862)

## v[0.1.3] (2024-08-15)

### Feature

- accept platform versions 242.* [`a0ee002`](https://github.com/IhToN/jb-webos-studio/commit/a0ee002)

### Build

- Bump org.jetbrains.changelog from 2.2.0 to 2.2.1 [`60b3d91`](https://github.com/IhToN/jb-webos-studio/commit/60b3d91)

### Other

- Merge pull request #33 from IhToN/dependabot/gradle/org.jetbrains.changelog-2.2.1 [`85d1732`](https://github.com/IhToN/jb-webos-studio/commit/85d1732)

## v[0.1.2] (2024-08-13)

### Documentation

- Set proper URL on the badges [`5ee831d`](https://github.com/IhToN/jb-webos-studio/commit/5ee831d)

### Build

- Bump org.jetbrains.qodana from 2024.1.4 to 2024.1.9 [`169f5c1`](https://github.com/IhToN/jb-webos-studio/commit/169f5c1)
- Bump JetBrains/qodana-action from 2024.1.4 to 2024.1.9 [`951774d`](https://github.com/IhToN/jb-webos-studio/commit/951774d)
- Bump org.jetbrains.kotlin.jvm from 1.9.24 to 2.0.10 [`c98d74c`](https://github.com/IhToN/jb-webos-studio/commit/c98d74c)
- Bump gradle/actions from 3 to 4 [`cf68f23`](https://github.com/IhToN/jb-webos-studio/commit/cf68f23)
- Bump org.jetbrains.intellij from 1.17.3 to 1.17.4 [`59069e3`](https://github.com/IhToN/jb-webos-studio/commit/59069e3)

### Chore

- set version to 0.1.2 [`95b49be`](https://github.com/IhToN/jb-webos-studio/commit/95b49be)

### Other

- Merge pull request #31 from IhToN/dependabot/gradle/org.jetbrains.qodana-2024.1.9 [`f4ad7f0`](https://github.com/IhToN/jb-webos-studio/commit/f4ad7f0)
- Merge pull request #32 from IhToN/dependabot/github_actions/JetBrains/qodana-action-2024.1.9 [`da522bb`](https://github.com/IhToN/jb-webos-studio/commit/da522bb)
- Merge pull request #30 from IhToN/dependabot/gradle/org.jetbrains.kotlin.jvm-2.0.10 [`61ce240`](https://github.com/IhToN/jb-webos-studio/commit/61ce240)
- Merge pull request #29 from IhToN/dependabot/github_actions/gradle/actions-4 [`0c0c36c`](https://github.com/IhToN/jb-webos-studio/commit/0c0c36c)
- Merge pull request #16 from IhToN/changelog-update-v0.1.1 [`b8aa849`](https://github.com/IhToN/jb-webos-studio/commit/b8aa849)
- Merge pull request #23 from IhToN/dependabot/gradle/org.jetbrains.intellij-1.17.4 [`a1c14f2`](https://github.com/IhToN/jb-webos-studio/commit/a1c14f2)
- Changelog update - v0.1.1 [`e0a915d`](https://github.com/IhToN/jb-webos-studio/commit/e0a915d)

## v[0.1.1] (2024-05-13)

### Bug Fixes

- Retrieve Devices from the User config, not the builtin config [`1110c54`](https://github.com/IhToN/jb-webos-studio/commit/1110c54)

### Chore

- Increase pluginVersion to 0.1.1 [`1bcbc75`](https://github.com/IhToN/jb-webos-studio/commit/1bcbc75)

### Other

- Merge pull request #15 from IhToN/changelog-update-v0.1.0 [`59285f1`](https://github.com/IhToN/jb-webos-studio/commit/59285f1)
- Changelog update - v0.1.0 [`4f72105`](https://github.com/IhToN/jb-webos-studio/commit/4f72105)

## v[0.1.0] (2024-05-10)

### Feature

- Use a Runnable Action for AresInstall and AresPackage [`773c9f8`](https://github.com/IhToN/jb-webos-studio/commit/773c9f8)
- Include a Node environment option [`20c446d`](https://github.com/IhToN/jb-webos-studio/commit/20c446d)
- Add a notification handler [`31e84dc`](https://github.com/IhToN/jb-webos-studio/commit/31e84dc)

### Documentation

- Remove non needed ToDo list [`16ceed8`](https://github.com/IhToN/jb-webos-studio/commit/16ceed8)
- Update the links of the badges [`b689fc6`](https://github.com/IhToN/jb-webos-studio/commit/b689fc6)

### Build

- Bump org.jetbrains:marketplace-zip-signer [`f529d4e`](https://github.com/IhToN/jb-webos-studio/commit/f529d4e)

### Chore

- Set PlatformType to PS and include JavaScript plugin [`a028dcf`](https://github.com/IhToN/jb-webos-studio/commit/a028dcf)

### Style

- Check and fix Code Analysis [`46f44a0`](https://github.com/IhToN/jb-webos-studio/commit/46f44a0)

### Other

- Merge pull request #14 from IhToN/dependabot/gradle/org.jetbrains-marketplace-zip-signer-0.1.24 [`1aeab35`](https://github.com/IhToN/jb-webos-studio/commit/1aeab35)
- release: Increase version to 0.1.0 [`5c9fea6`](https://github.com/IhToN/jb-webos-studio/commit/5c9fea6)

## v[0.0.1] (2024-05-09)

### Feature

- Enable marketplace-zip-signer plugin [`dbb2f83`](https://github.com/IhToN/jb-webos-studio/commit/dbb2f83)
- Add Logo and Icon [`185819a`](https://github.com/IhToN/jb-webos-studio/commit/185819a)
- Add action and group text to messages bundle [`bb29fef`](https://github.com/IhToN/jb-webos-studio/commit/bb29fef)
- Display a list of configured devices in Ares Install [`bb7ab81`](https://github.com/IhToN/jb-webos-studio/commit/bb7ab81)
- Add Settings with Persistence and store the Ares CLI Path [`c7e801b`](https://github.com/IhToN/jb-webos-studio/commit/c7e801b)
- Add ares-generate and ares-package commands [`ee9b142`](https://github.com/IhToN/jb-webos-studio/commit/ee9b142)

### Bug Fixes

- Apply Qodana suggestions [`2673a71`](https://github.com/IhToN/jb-webos-studio/commit/2673a71)

### Documentation

- Add GPLv3 LICENSE.md [`ac0a1a6`](https://github.com/IhToN/jb-webos-studio/commit/ac0a1a6)
- Include plugin description and installation instructions [`4c2ef0b`](https://github.com/IhToN/jb-webos-studio/commit/4c2ef0b)

### Build

- Bump org.jetbrains.qodana from 2023.3.1 to 2024.1.4 [`e22c0f1`](https://github.com/IhToN/jb-webos-studio/commit/e22c0f1)
- Bump JetBrains/qodana-action from 2023.3.1 to 2024.1.4 [`2b11d1c`](https://github.com/IhToN/jb-webos-studio/commit/2b11d1c)
- Bump org.jetbrains.kotlin.jvm from 1.9.23 to 1.9.24 [`1165252`](https://github.com/IhToN/jb-webos-studio/commit/1165252)
- Bump org.jetbrains.kotlinx:kotlinx-serialization-json [`df08aa7`](https://github.com/IhToN/jb-webos-studio/commit/df08aa7)

### Chore

- Update plugin name and description [`ef5eb24`](https://github.com/IhToN/jb-webos-studio/commit/ef5eb24)

### Other

- Merge remote-tracking branch 'origin/main' [`449b4e9`](https://github.com/IhToN/jb-webos-studio/commit/449b4e9)
- Merge pull request #11 from IhToN/dependabot/github_actions/JetBrains/qodana-action-2024.1.4 [`baf294b`](https://github.com/IhToN/jb-webos-studio/commit/baf294b)
- Merge pull request #10 from IhToN/dependabot/gradle/org.jetbrains.qodana-2024.1.4 [`523f7b4`](https://github.com/IhToN/jb-webos-studio/commit/523f7b4)
- Merge pull request #9 from IhToN/dependabot/gradle/org.jetbrains.kotlin.jvm-1.9.24 [`0fb46fe`](https://github.com/IhToN/jb-webos-studio/commit/0fb46fe)
- Merge pull request #8 from IhToN/dependabot/gradle/org.jetbrains.kotlinx-kotlinx-serialization-json-1.6.3 [`629b1da`](https://github.com/IhToN/jb-webos-studio/commit/629b1da)
- Merge pull request #4 from IhToN/dependabot/github_actions/gradle/wrapper-validation-action-3 [`d38bbd3`](https://github.com/IhToN/jb-webos-studio/commit/d38bbd3)
- Merge pull request #3 from IhToN/dependabot/github_actions/jtalk/url-health-check-action-4 [`7f0f597`](https://github.com/IhToN/jb-webos-studio/commit/7f0f597)
- Merge pull request #1 from IhToN/dependabot/gradle/org.jetbrains.intellij-1.17.3 [`210a9c2`](https://github.com/IhToN/jb-webos-studio/commit/210a9c2)
- Bump gradle/wrapper-validation-action from 2 to 3 [`f9852e9`](https://github.com/IhToN/jb-webos-studio/commit/f9852e9)
- Bump jtalk/url-health-check-action from 3 to 4 [`90f2045`](https://github.com/IhToN/jb-webos-studio/commit/90f2045)
- Bump org.jetbrains.intellij from 1.17.2 to 1.17.3 [`459b7d6`](https://github.com/IhToN/jb-webos-studio/commit/459b7d6)
- Template cleanup [`600489f`](https://github.com/IhToN/jb-webos-studio/commit/600489f)
- Initial commit [`ed31d08`](https://github.com/IhToN/jb-webos-studio/commit/ed31d08)

[Unreleased]: https://github.com/IhToN/jb-webos-studio/compare/v0.1.5...HEAD
[0.1.5]: https://github.com/IhToN/jb-webos-studio/compare/v0.1.4...v0.1.5
[0.1.4]: https://github.com/IhToN/jb-webos-studio/compare/v0.1.3...v0.1.4
[0.1.3]: https://github.com/IhToN/jb-webos-studio/compare/v0.1.2...v0.1.3
[0.1.2]: https://github.com/IhToN/jb-webos-studio/compare/v0.1.1...v0.1.2
[0.1.1]: https://github.com/IhToN/jb-webos-studio/compare/v0.1.0...v0.1.1
[0.1.0]: https://github.com/IhToN/jb-webos-studio/compare/v0.0.1...v0.1.0
[0.0.1]: https://github.com/IhToN/jb-webos-studio/commits/v0.0.1
