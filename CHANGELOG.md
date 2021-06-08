# Changelog
All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](http://keepachangelog.com/en/1.0.0/).

## [Unreleased]

## Added
- clj-kondo linter configuration files.

## [0.4.0] - 2021-06-05

## Changed
- **Breaking Change** Reverted the change of the value of the `:main` build option, back to `project-name.client`.

## [0.3.0] - 2021-06-04

## Changed
- **Breaking Change** Changed the value of the `:main` build option from `project-name.client` to `project-name.client.main`.

## Added
- **Breaking Change** Support added for `:port`, `:host` and `watch-dirs` Figwheel-main options. Now the `:figwheel-main` key takes a configuration map, instead of a boolean value.

## [0.2.1] - 2020-12-16

## Added
- Use parallel builds for ClojureScript compilation

## [0.2.0] - 2020-09-14

## Added
- Support for figwheel-main config (add `:figwheel-main true` to module options)

## Deprecated
- Despite `lein-figwheel` still being used by default in order to maintain
backwards-compatibility, it's deprecated now.

## [0.1.11] - 2020-05-21

## Removed
- Remove unnecessary goog.debug closure define
- Remove unnecessary devtools.preload dependency

## [0.1.9] - 2020-05-01

## Changed
- Update dependencies versions, including ClojureScript version

## [0.1.8] - 2019-07-26

## Changed
- Update ClojureScript dependency version

## [0.1.7] - 2019-07-23

## Changed
- `:add-api-example?` option is no longer used (and is now ignored).
- Moved all non-Clojurescript config back to Hydrogen Duct Template. This version should only be used with Hydrogen Duct template 0.1.8 or newer

## [0.1.6] - 2019-07-23

### Added
- tests

## [0.1.5] - 2019-06-13

### Changed
- Split concerns of ancestor `:hydrogen.module.cljs` into separate repos.

## [0.1.4] - 2019-05-29

### Added
- You can now configure which environment would get what externs.
You can keep using `:externs-paths ["a" "b"]` which would set externs both
for `:development` and `:production` builds. If you want to distinguish them,
you can now do `:externs-paths {:development ["a" "b"] :production ["x" "y"]}`.

### Changed
- `:duct.server/figwheel` key is modified only when in `:development` environment.
- `:duct.compiler/cljs` key is modified only when in `:production` environment.

## [0.1.3] - 2019-05-27

### Added
- You can configure this module to provide specific paths for extern files by using
`:extern-paths ["x/y/extern1.js" "x/y/extern2.js"]`. If not provided, it will assume that there's one
extern file in location `src/<project-dirs>/client/externs.js`.

### Changed
- **Breaking change** - Now by default this module doesn't provide example api integrant key.
To retain it, you have to add `:add-example-api? true` to the config options.

[UNRELEASED]: https://github.com/magnetcoop/hydrogen.module.core/compare/v0.4.0...HEAD
[0.4.0]: https://github.com/magnetcoop/hydrogen.module.core/releases/tag/v0.4.0
[0.3.0]: https://github.com/magnetcoop/hydrogen.module.core/releases/tag/v0.3.0
[0.2.3]: https://github.com/magnetcoop/hydrogen.module.core/releases/tag/v0.2.2
[0.2.1]: https://github.com/magnetcoop/hydrogen.module.core/releases/tag/v0.2.1
[0.2.0]: https://github.com/magnetcoop/hydrogen.module.core/releases/tag/v0.2.0
[0.1.11]: https://github.com/magnetcoop/hydrogen.module.core/releases/tag/v0.1.11
[0.1.9]: https://github.com/magnetcoop/hydrogen.module.core/releases/tag/v0.1.9
[0.1.8]: https://github.com/magnetcoop/hydrogen.module.core/releases/tag/v0.1.8
[0.1.7]: https://github.com/magnetcoop/hydrogen.module.core/releases/tag/v0.1.7
[0.1.6]: https://github.com/magnetcoop/hydrogen.module.core/releases/tag/v0.1.6
[0.1.5]: https://github.com/magnetcoop/hydrogen.module.core/releases/tag/v0.1.5
[0.1.4]: https://github.com/magnetcoop/hydrogen.module.cljs/releases/tag/v0.1.4
[0.1.3]: https://github.com/magnetcoop/hydrogen.module.cljs/releases/tag/v0.1.3

