# Changelog
All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](http://keepachangelog.com/en/1.0.0/).

## [Unreleased]

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

[0.1.3]: https://github.com/magnetcoop/hydrogen.module.cljs/releases/tag/v0.1.3
[0.1.4]: https://github.com/magnetcoop/hydrogen.module.cljs/releases/tag/v0.1.4
[0.1.5]: https://github.com/magnetcoop/hydrogen.module.core/releases/tag/v0.1.5
[0.1.6]: https://github.com/magnetcoop/hydrogen.module.core/releases/tag/v0.1.6
[0.1.7]: https://github.com/magnetcoop/hydrogen.module.core/releases/tag/v0.1.7
[UNRELEASED]:  https://github.com/magnetcoop/hydrogen.module.core/compare/v0.1.7...HEAD
