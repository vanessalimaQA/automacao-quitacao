param(
    [Parameter(Mandatory = $true)]
    [string]$Package,

    [Parameter(Mandatory = $true)]
    [string]$ClassName
)

$base = "src\main\java\com\automation"

$packagePath = $Package.Replace(".", "\")

$folder = Join-Path $base $packagePath

if (!(Test-Path $folder)) {
    New-Item -ItemType Directory -Path $folder -Force | Out-Null
}

$file = Join-Path $folder "$ClassName.java"

if (Test-Path $file) {
    throw "O arquivo já existe e não será sobrescrito: $file"
}

$packageDeclaration = "package com.automation.$Package;"

$content = @"
$packageDeclaration

public class $ClassName {

}
"@

$utf8SemBom = New-Object System.Text.UTF8Encoding($false)

[System.IO.File]::WriteAllText(
    $file,
    $content,
    $utf8SemBom
)

Write-Host ""
Write-Host "Classe criada com sucesso!" -ForegroundColor Green
Write-Host $file