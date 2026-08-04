param(
    [Parameter(Mandatory = $true)]
    [string]$Package,

    [Parameter(Mandatory = $true)]
    [string]$ClassName
)

$base = "src\test\java\com\automation\tests"

$packagePath = $Package.Replace(".", "\")

$folder = Join-Path $base $packagePath

if (!(Test-Path $folder)) {
    New-Item `
        -ItemType Directory `
        -Path $folder `
        -Force |
        Out-Null
}

$file = Join-Path $folder "$ClassName.java"

if (Test-Path $file) {
    throw "O arquivo já existe e não será sobrescrito: $file"
}

$packageDeclaration =
        "package com.automation.tests.$Package;"

$content = @"
$packageDeclaration

import org.junit.jupiter.api.Test;

class $ClassName {

    @Test
    void deveExecutarCenario() {

    }
}
"@

$utf8SemBom =
        New-Object System.Text.UTF8Encoding($false)

[System.IO.File]::WriteAllText(
    $file,
    $content,
    $utf8SemBom
)

Write-Host ""
Write-Host "Teste criado com sucesso!" `
    -ForegroundColor Green
Write-Host $file