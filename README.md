# webOS Studio

![Build](https://github.com/IhToN/jb-webos-studio/workflows/Build/badge.svg)
[![Version](https://img.shields.io/jetbrains/plugin/v/PLUGIN_ID.svg)](https://plugins.jetbrains.com/plugin/PLUGIN_ID)
[![Downloads](https://img.shields.io/jetbrains/plugin/d/PLUGIN_ID.svg)](https://plugins.jetbrains.com/plugin/PLUGIN_ID)

## Template ToDo list
- [x] Create a new [IntelliJ Platform Plugin Template][template] project.
- [x] Get familiar with the [template documentation][template].
- [x] Adjust the [pluginGroup](./gradle.properties), [plugin ID](./src/main/resources/META-INF/plugin.xml) and [sources package](./src/main/kotlin).
- [x] Adjust the plugin description in `README` (see [Tips][docs:plugin-description])
- [x] Review the [Legal Agreements](https://plugins.jetbrains.com/docs/marketplace/legal-agreements.html?from=IJPluginTemplate).
- [ ] [Publish a plugin manually](https://plugins.jetbrains.com/docs/intellij/publishing-plugin.html?from=IJPluginTemplate) for the first time.
- [ ] Set the `PLUGIN_ID` in the above README badges.
- [ ] Set the [Plugin Signing](https://plugins.jetbrains.com/docs/intellij/plugin-signing.html?from=IJPluginTemplate) related [secrets](https://github.com/JetBrains/intellij-platform-plugin-template#environment-variables).
- [ ] Set the [Deployment Token](https://plugins.jetbrains.com/docs/marketplace/plugin-upload.html?from=IJPluginTemplate).
- [ ] Click the <kbd>Watch</kbd> button on the top of the [IntelliJ Platform Plugin Template][template] to be notified about releases containing new features and fixes.

<!-- Plugin description -->
<h3>Accelerate SmartTV App Development with webOS Studio</h3>
<p>Create, bundle, and deploy webOS applications effortlessly within your JetBrains IDE.</p>
<p>webOS Studio simplifies the development process by providing intuitive tools with the usage of @webos-tools/cli:</p>
<ul>
<li>Create new webOS Webapp projects with ease.</li>
<li>Bundle applications into IPK packages seamlessly.</li>
<li>Effortlessly install bundled IPKs onto selected devices.</li>
</ul>
<!-- Plugin description end -->

## Installation

- Using the IDE built-in plugin system:
  
  1. <kbd>Settings/Preferences</kbd> > <kbd>Plugins</kbd> > <kbd>Marketplace</kbd> > <kbd>Search for "webOS Studio"</kbd> >
  <kbd>Install</kbd>
  2. Make sure you have installed [@webos-tools/cli](https://github.com/webos-tools/cli).
  3. <kbd>Settings/Preferences</kbd> > <kbd>Tools</kbd> > <kbd>webOS Studio</kbd> > <kbd>Set up your webos-tools/cli folder.</kbd>
  
- Manually:

  1. Download the [latest release](https://github.com/IhToN/jb-webos-studio/releases/latest) and install it manually using
  <kbd>Settings/Preferences</kbd> > <kbd>Plugins</kbd> > <kbd>⚙️</kbd> > <kbd>Install plugin from disk...</kbd>
  2. Make sure you have installed [@webos-tools/cli](https://github.com/webos-tools/cli).
  3. <kbd>Settings/Preferences</kbd> > <kbd>Tools</kbd> > <kbd>webOS Studio</kbd> > <kbd>Set up your webos-tools/cli folder.</kbd>

---
Plugin based on the [IntelliJ Platform Plugin Template][template].

[template]: https://github.com/JetBrains/intellij-platform-plugin-template
[docs:plugin-description]: https://plugins.jetbrains.com/docs/intellij/plugin-user-experience.html#plugin-description-and-presentation
